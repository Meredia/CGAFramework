package cga.exercise.components.camera

import cga.exercise.components.geometry.Transformable
import cga.exercise.components.shader.ShaderProgram
import org.joml.Math
import org.joml.Matrix4f

class TronCamera(var fieldOfView : Float = Math.toRadians(90f), var aspectRatio : Float = 16f/9f, var nearPlane : Float = 0.1f, var farPlane : Float = 100f) : ICamera, Transformable(){  //Aufgabe 2.4.1

    /*
     * Calculate the ViewMatrix according the lecture
     * values needed:
     *  - eye –> the position of the camera
     *  - center –> the point in space to look at
     *  - up –> the direction of 'up'
     */
    override fun getCalculateViewMatrix(): Matrix4f {
        return Matrix4f().lookAt(getWorldPosition(), getWorldPosition().sub(getWorldZAxis()), getWorldYAxis())
    }

    /*
     * Calculate the ProjectionMatrix according the lecture
     * values needed:
     *  - fov – the vertical field of view in radians (must be greater than zero and less than PI)
     *  - aspect – the aspect ratio (i.e. width / height; must be greater than zero)
     *  - zNear – near clipping plane distance
     *  - zFar – far clipping plane distance
     */
    override fun getCalculateProjectionMatrix(): Matrix4f {
        return Matrix4f().perspective(fieldOfView, aspectRatio, nearPlane, farPlane)  //Wenn die perspektivische Projektionsmatrix eingerichtet ist, wird der Abstand zur nahen Ebene und zur fernen Ebene festgelegt.
    }

    override fun bind(shader: ShaderProgram) {
        shader.use()
        shader.setUniform("view", getCalculateViewMatrix(), false)
        shader.setUniform("projection", getCalculateProjectionMatrix(), false)
    }
}