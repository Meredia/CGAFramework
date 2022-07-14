package cga.exercise.components.geometry

import cga.exercise.components.shader.ShaderProgram
import org.joml.Matrix4f

class Renderable (modelMatrix: Matrix4f = Matrix4f(), parent: Transformable? = null, var meshes : MutableList<Mesh> = mutableListOf()) : IRenderable,
    Transformable(modelMatrix, parent) {
    override fun render(shaderProgram: ShaderProgram) {
        shaderProgram.setUniform("model_matrix", getWorldModelMatrix(), false)  //getWorldModelMatrix aus der Klasse Transformable, Uniform für den Shader
        meshes.forEach{   //render für jedes Mesh
            it.render()
        }
    }
}

