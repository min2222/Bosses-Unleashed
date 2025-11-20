#version 330

uniform sampler2D DiffuseSampler;
uniform sampler2D DepthSampler;
uniform sampler2D ImageSampler;

uniform ivec4 iResolution;
uniform vec2 OutSize;
uniform float iTime;

in vec2 texCoord;
in vec4 near_4;
in vec4 far_4;

out vec4 fragColor;

#define near 0.05
#define far 1000.0
float linearizeDepth(float depth) {
    float z = depth * 2.0 - 1.0;
    return (2.0 * near * far) / (far + near - z * (far - near));
}

const float pi = 3.1415927;

//From Dave (https://www.shadertoy.com/view/XlfGWN)
float hash13(vec3 p){
	p  = fract(p * vec3(.16532,.17369,.15787));
    p += dot(p.xyz, p.yzx + 19.19);
    return fract(p.x * p.y * p.z);
}

float sdSphere( vec3 p, float s )
{
  return length(p)-s;
}

float sdCappedCylinder( vec3 p, vec2 h )
{
  vec2 d = abs(vec2(length(p.xz),p.y)) - h;
  return min(max(d.x,d.y),0.0) + length(max(d,0.0));
}

float sdTorus( vec3 p, vec2 t )
{
  vec2 q = vec2(length(p.xz)-t.x,p.y);
  return length(q)-t.y;
}

void main() {
    vec3 ro = near_4.xyz / near_4.w;
    vec3 far3 = far_4.xyz / far_4.w;
    vec3 rd = normalize(far3 - ro);
    
    ro /= iTime * 50.0;
    
  	vec3 bg = texture(DiffuseSampler, texCoord).rgb;
    float depth = linearizeDepth(texture(DepthSampler, texCoord).r);
    
    vec3 bh = vec3(0.0,0.0,0.0);
    float bhr = 0.3;
    float bhmass = 5.0;
   	bhmass *= 0.001; // premul G
    
    vec3 p = ro;
    vec3 pv = rd;
    
    p += pv * hash13(rd + vec3(iTime)) * 0.02;
    
    float dt = 0.02;
    
    vec3 col = vec3(0.0);
    
    float noncaptured = 1.0;
    
    vec3 c1 = vec3(0.5,0.35,0.1);
    vec3 c2 = vec3(1.0,0.8,0.6);
    
	for(int i = 0; i < 100; i++)
    {
    	if(i > depth) break;
    	
        p += pv * dt * noncaptured;
        
        // gravity
        vec3 bhv = bh - p;
        float r = dot(bhv,bhv);
        pv += normalize(bhv) * ((bhmass) / r);
        
        noncaptured = smoothstep(0.0,0.0001,sdSphere(p-bh,bhr));
        
        // texture the disc
        // need polar coordinates of xz plane
        float dr = length(bhv.xz);
        float da = atan(bhv.x,bhv.z);
        vec2 ra = vec2(dr,da * (0.01 + (dr - bhr)*0.002) + 2.0 * pi + iTime*0.02 );
        ra *= vec2(10.0,20.0);
        
        vec3 dcol = mix(c2,c1,pow(length(bhv)-bhr,2.0)) * max(0.0,texture(ImageSampler,ra*vec2(0.1,0.5)).r+0.05) * (4.0 / ((0.001+(length(bhv) - bhr)*50.0) ));
        
        col += max(vec3(0.0),dcol * step(0.0,-sdTorus( (p * vec3(1.0,50.0,1.0)) - bh, vec2(0.8,0.99))) * noncaptured);
        
        // glow
        col += vec3(1.0,0.9,0.7) * (1.0/vec3(dot(bhv,bhv))) * 0.003 * noncaptured;
    }
    
	if(noncaptured < 1.0) {
		fragColor = vec4(vec3(0.0), 1.0);
	}
	else {
		col = mix(bg, col, length(col));
   		fragColor = vec4(col, 1.0);
	}
}