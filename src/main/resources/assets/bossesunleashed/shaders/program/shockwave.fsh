#version 330

uniform sampler2D DiffuseSampler;
uniform sampler2D DepthSampler;
uniform sampler2D ImageSampler;

uniform ivec2 iResolution;
uniform vec2 OutSize;
uniform float iTime;

uniform mat4 ViewMat;
uniform mat4 ProjectionMat;

in vec2 texCoord;
in vec4 near_4;
in vec4 far_4;

out vec4 fragColor;

#define MAX_STEPS 100
#define MAX_DIST 100.
#define SURF_DIST .001

#define near 0.05
#define far 1000.0
float linearizeDepth(float depth) {
    float z = depth * 2.0 - 1.0;
    return (2.0 * near * far) / (far + near - z * (far - near));
}

float sdSphere(vec3 p, float s)
{
  	return length(p)-s;
}

const vec3 sphere_center_world = vec3(0.0);

float map(vec3 ro, vec3 rd, float s)
{
	float dO=0.;
    
    for(int i=0; i<MAX_STEPS; i++) {
    	vec3 p = ro + rd*dO;
        float dS = sdSphere(p, s);
        dO += dS;
        if(dO>MAX_DIST || abs(dS)<SURF_DIST) break;
    }
    
    return dO;
}

void main() {
    vec3 ro = near_4.xyz / near_4.w;
    vec3 rd = normalize(far_4.xyz / far_4.w - ro);

    vec2 uv = gl_FragCoord.xy / iResolution.xy;
    
    vec4 sphere_center_clip = ProjectionMat * ViewMat * vec4(sphere_center_world, 1.0);
    vec3 sphere_center_ndc = sphere_center_clip.xyz / sphere_center_clip.w;
    vec2 sphere_center_uv = sphere_center_ndc.xy * 0.5 + 0.5;

    vec2 sphere_uv_centered = uv - sphere_center_uv;
    sphere_uv_centered.x *= float(iResolution.x) / float(iResolution.y);

    vec2 distortionDir = normalize(-sphere_uv_centered);
    if (length(sphere_uv_centered) < 0.001) distortionDir = vec2(1.0, 0.0);
    
    float factor = 0.0;

    float deltaTime = fract(iTime * 0.1);
	float radius = sqrt(2.) * 10.0 *deltaTime;
    float shockStrength = 1.;
    float circle = radius - distance(distortionDir, sphere_uv_centered);
    
    float t = map(ro, rd, radius);
    
    if (t > 0.0) {
        vec3 p_intersect = ro + rd * t;
        factor = shockStrength * sin(deltaTime*3.14) * pow(clamp(1. - abs(circle), 0., 1.), 10.);
    }
    
    float lensingSpread = 0.12;
    
    vec3 color = mix(
        vec3(
            texture(DiffuseSampler, uv + (lensingSpread)*factor*distortionDir).x,
            texture(DiffuseSampler, uv + (lensingSpread*1.2)*factor*distortionDir).y,
            texture(DiffuseSampler, uv + (lensingSpread*1.5)*factor*distortionDir).z
        ),
        vec3(
            texture(DiffuseSampler, uv + (lensingSpread)*factor*distortionDir).x,
            texture(DiffuseSampler, uv + (lensingSpread*1.2)*factor*distortionDir).y,
            texture(DiffuseSampler, uv + (lensingSpread*1.5)*factor*distortionDir).z
        ), clamp(deltaTime+circle*5., 0., 1.)
    );
    
    fragColor = vec4(color, 1.0);
}