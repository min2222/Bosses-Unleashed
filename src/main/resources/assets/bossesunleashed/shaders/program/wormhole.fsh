#version 330

uniform sampler2D DiffuseSampler;
uniform sampler2D DepthSampler;

uniform mat4 ProjectionMatrix;
uniform mat4 ViewMatrix;

uniform ivec4 iResolution;
uniform vec2 OutSize;
uniform float iTime;

in vec2 texCoord;
in vec4 near_4;
in vec4 far_4;

out vec4 fragColor;

const float inf = uintBitsToFloat(0x7F800000u);

#define near 0.05
#define far 1000.0
float linearizeDepth(float depth) {
    float z = depth * 2.0 - 1.0;
    return (2.0 * near * far) / (far + near - z * (far - near));
}

#define iterations 17
#define formuparam 0.53

#define volsteps 20
#define stepsize 0.1

#define zoom   0.800
#define tile   0.850
#define speed  0.010 

#define brightness 0.0015
#define darkmatter 0.300
#define distfading 0.730
#define saturation 0.850

vec4 Sphere(vec3 ro, vec3 rd, float r) {
    vec3 rc = ro;
    float c = dot(rc, rc) - (r*r);
    float b = dot(rd, rc);
    float d = b * b - c;
    float t = -b - sqrt(abs(d));
    float st = step(0, min(t,d));
    t = mix(-1, t, st);
    if (t < 0) t = inf;
    vec3 norm = normalize(ro+rd*t);
    return vec4(norm, t);
}

vec3 iPlane(vec3 ro, vec3 rd, vec3 po, vec3 pd){
    float d = dot(po - ro, pd) / dot(rd, pd);
    return d * rd + ro;
}

vec3 render(vec2 uv, float maindepth, vec3 col, vec3 ro, vec3 rd) {
    vec4 hit;
    hit = Sphere(ro, rd, 10.0);
    vec3 pos = iPlane(ro, rd, vec3(0.0), normalize(ro));
    float intensity = dot(pos, pos);
    if(intensity > length(hit)) {
        intensity = 1.0 / intensity;
        vec4 worldPos = vec4(hit.xyz, 1.0);
        vec4 screenPos = ProjectionMatrix * ViewMatrix * worldPos;
       	vec2 screencenter = (screenPos.xy / screenPos.w) * 0.5 + 0.5;
        vec2 dir = 0.5 * normalize(texCoord - screencenter);
    	return texture(DiffuseSampler, texCoord - dir * intensity).rgb;
    }
    hit = Sphere(ro, rd, 4.0);
	if (intensity < length(hit)) {
	    vec3 dir = rd;
		vec3 from=vec3(1.,.5,0.5);
		//volumetric rendering
		float s=0.1,fade=1.;
		vec3 v=vec3(0.);
		for (int r=0; r<volsteps; r++) {
			if(s > maindepth) break;
			vec3 p=from+s*dir*.5;
			p=abs(p)/max(dot(p,p),0.0016)-formuparam; // the magic formula
			float pa,a=pa=0.;
			for (int i=0; i<iterations; i++) { 
				p=abs(p)/dot(p,p)-formuparam; // the magic formula
				a+=abs(length(p)-pa); // absolute sum of average change
				pa=length(p);
			}
			float dm=max(0.,darkmatter-a*a*.001); //dark matter
			a*=a*a; // add contrast
			if (r>6) fade*=1.-dm; // dark matter, don't render near
			v+=fade;
			v+=vec3(s,s*s,s*s*s*s)*a*brightness*fade; // coloring based on distance
			fade*=distfading; // distance fading
			s+=stepsize;
		}
		v=mix(vec3(length(v)),v,saturation); //color adjust
		return v*.01;
	}
    return col;
}

void main() {
    vec3 ro = near_4.xyz / near_4.w;
    vec3 far3 = far_4.xyz / far_4.w;
    vec3 rd = normalize(far3 - ro);
    
    vec2 uv = texCoord;
    uv.y = clamp(uv.y, 2 / OutSize.y, 1);
    vec4 image = texture(DiffuseSampler, uv);
    float depth = linearizeDepth(texture(DepthSampler, texCoord).r);
    
    vec3 color = render(uv, depth, image.rgb, ro, rd);
    fragColor = vec4(color, 1.0);
}