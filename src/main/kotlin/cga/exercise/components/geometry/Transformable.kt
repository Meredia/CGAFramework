package cga.exercise.components.geometry

import org.joml.Matrix4f
import org.joml.Vector3f

open class Transformable(private var modelMatrix: Matrix4f = Matrix4f(), var parent: Transformable? = null) {
    /**
     * Returns copy of object model matrix
     * @return modelMatrix
     */
    fun getModelMatrix(): Matrix4f {   //Die Matrix M, die alle Translationen, Rotationen oder Skalierungen enthält, die auf ein Objekt angewendet werden, wird in OpenGL als Modellmatrix bezeichnet
        // TODO implement
        return Matrix4f(modelMatrix)

        //throw NotImplementedError()
    }

    /**
     * Returns multiplication of world and object model matrices.
     * Multiplication has to be recursive for all parents.
     * Hint: scene graph
     * @return world modelMatrix
     */
    fun getWorldModelMatrix(): Matrix4f {
        var modeMatrix = getModelMatrix()
        parent?.getWorldModelMatrix()?.mul(modelMatrix, modeMatrix)      //?. führt einen sicheren Aufruf durch (ruft eine Methode auf oder greift auf eine Eigenschaft zu, wenn der Empfänger nicht leer ist)
        return modeMatrix

       // throw NotImplementedError()
    }

    /**
     * Rotates object around its own origin.
     * @param pitch radiant angle around x-axis ccw
     * @param yaw radiant angle around y-axis ccw
     * @param roll radiant angle around z-axis ccw
     */
    fun rotate(pitch: Float, yaw: Float, roll: Float) {
        // TODO implement
        modelMatrix = modelMatrix.rotateXYZ(pitch, yaw, roll) // pitch neigung - yaw seitlich lenken

        /* throw NotImplementedError()*/
    }

    /**
     * Rotates object around given rotation center.
     * @param pitch radiant angle around x-axis ccw
     * @param yaw radiant angle around y-axis ccw
     * @param roll radiant angle around z-axis ccw
     * @param altMidpoint rotation center
     */
    fun rotateAroundPoint(pitch: Float, yaw: Float, roll: Float, altMidpoint: Vector3f) {
        // TODO implement
        var matrix = Matrix4f()
        matrix.translate(altMidpoint)  //Translation einer Matrix - verschieben eines Objekts
        matrix.rotateXYZ(pitch, yaw, roll)
        matrix.translate(Vector3f(altMidpoint).negate())  //negate matrix also -1 anstelle 1 - also zurück verschieben
        modelMatrix = matrix.mul(modelMatrix)

       // throw NotImplementedError()
    }

    /**
     * Translates object based on its own coordinate system.
     * @param deltaPos delta positions
     */
    fun translate(deltaPos: Vector3f) {
        // TODO implement
        modelMatrix = modelMatrix.translate(deltaPos)

        // throw NotImplementedError()
    }

    /**
     * Translates object based on its parent coordinate system.
     * Hint: this operation has to be left-multiplied
     * @param deltaPos delta positions (x, y, z)
     */
    fun preTranslate(deltaPos: Vector3f) {
        // TODO implement
        val world_modelmatrix = Matrix4f().translate(deltaPos)
        modelMatrix = world_modelmatrix.mul(modelMatrix)

        /* throw NotImplementedError()*/
    }

    /**
     * Scales object related to its own origin
     * @param scale scale factor (x, y, z)
     */
    fun scale(scale: Vector3f) {
        // TODO implement
        modelMatrix = modelMatrix.scale(scale)

       // throw NotImplementedError()
    }

    /**
     * Returns position based on aggregated translations.
     * Hint: last column of model matrix
     * @return position
     */
    fun getPosition(): Vector3f {
        // TODO implement
        return Vector3f(modelMatrix.m30(), modelMatrix.m31(), modelMatrix.m32())  // letzte Reihe ist m30-31-32

       // throw NotImplementedError()
    }

    /**
     * Returns position based on aggregated translations incl. parents.
     * Hint: last column of world model matrix
     * @return position
     */
    fun getWorldPosition(): Vector3f {
        // TODO implement
        val world_modelmatrix = getWorldModelMatrix()
        return Vector3f(world_modelmatrix.m30(), world_modelmatrix.m31(), world_modelmatrix.m32())

       // throw NotImplementedError()
    }

    /**
     * Returns x-axis of object coordinate system
     * Hint: first normalized column of model matrix
     * @return x-axis
     */
    fun getXAxis(): Vector3f {
        // TODO implement
        return Vector3f(modelMatrix.m00(), modelMatrix.m01(), modelMatrix.m02()).normalize()

       // throw NotImplementedError()
    }

    /**
     * Returns y-axis of object coordinate system
     * Hint: second normalized column of model matrix
     * @return y-axis
     */
    fun getYAxis(): Vector3f {
        // TODO implement
        return Vector3f(modelMatrix.m10(), modelMatrix.m11(), modelMatrix.m12()).normalize()

       // throw NotImplementedError()
    }

    /**
     * Returns z-axis of object coordinate system
     * Hint: third normalized column of model matrix
     * @return z-axis
     */
    fun getZAxis(): Vector3f {
        // TODO implement
        return Vector3f(modelMatrix.m20(), modelMatrix.m21(), modelMatrix.m22()).normalize()

      //  throw NotImplementedError()
    }

    /**
     * Returns x-axis of world coordinate system
     * Hint: first normalized column of world model matrix
     * @return x-axis
     */
    fun getWorldXAxis(): Vector3f {
        // TODO implement
        var world_modelmatrix = getWorldModelMatrix()
        return Vector3f(world_modelmatrix.m00(), world_modelmatrix.m01(), world_modelmatrix.m02()).normalize()

       // throw NotImplementedError()
    }

    /**
     * Returns y-axis of world coordinate system
     * Hint: second normalized column of world model matrix
     * @return y-axis
     */
    fun getWorldYAxis(): Vector3f {
        // TODO implement
        var world_modelmatrix = getWorldModelMatrix()
        return Vector3f(world_modelmatrix.m10(), world_modelmatrix.m11(), world_modelmatrix.m12()).normalize()

      //  throw NotImplementedError()
    }

    /**
     * Returns z-axis of world coordinate system
     * Hint: third normalized column of world model matrix
     * @return z-axis
     */
    fun getWorldZAxis(): Vector3f {
        // TODO implement
        var world_modelmatrix = getWorldModelMatrix()
        return Vector3f(world_modelmatrix.m20(), world_modelmatrix.m21(), world_modelmatrix.m22()).normalize()

      //  throw NotImplementedError()
    }
}