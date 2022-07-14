#version 330 core

layout(location = 0) in vec3 position;
layout(location = 2) in vec3 normal;    //new line, Farbe von den Dreiecken wird hier angepasst; 1= bunte Ausgabe, 3=schwarz

//uniforms
// translation object to world
uniform mat4 model_matrix;
uniform mat4 view; //new line
uniform mat4 projection; //new line

out struct VertexData
{
    vec3 normal;
} vertexData;

//
void main(){
   mat4 modelview = view * model_matrix;  //new line; richtige Bewegungsablauf von shepre und ground
    vec4 pos = modelview * vec4(position, 1.0f);     //Die Entfernung der Kamera

    vec4 norm = transpose(inverse(modelview)) *  vec4(normal, 0.0f);

    gl_Position = projection * pos;
    vertexData.normal = norm.rgb;
}



