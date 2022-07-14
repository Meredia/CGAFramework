#version 330 core

//input from vertex shader
in struct VertexData
{
    vec3 normal; //angepasst
} vertexData;

//fragment shader output
out vec4 color;


void main(){
    vec3 normal = normalize(vertexData.normal); //new line
    color = vec4(abs(normal.r), abs(normal.g), abs(normal.b), 1.0f); //angepasste line

}