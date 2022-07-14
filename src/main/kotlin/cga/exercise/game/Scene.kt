package cga.exercise.game

import cga.exercise.components.geometry.Renderable
import cga.exercise.components.camera.TronCamera
import cga.exercise.components.geometry.Mesh
import cga.exercise.components.shader.ShaderProgram
import cga.framework.GameWindow
import cga.exercise.components.geometry.VertexAttribute
import cga.framework.GLError
import org.lwjgl.opengl.GL11.*
import cga.framework.OBJLoader
import org.joml.Matrix4f
import org.joml.Vector3f
import org.joml.Math
import org.lwjgl.glfw.GLFW
import org.joml.*



/**
 * Created by Fabian on 16.09.2017.
 */
class Scene(private val window: GameWindow) {   //var mesh: Mesh
    private val staticShader: ShaderProgram = ShaderProgram("assets/shaders/tron_vert.glsl", "assets/shaders/tron_frag.glsl")

    // private val staticShader: ShaderProgram = ShaderProgram("assets/shaders/simple_vert.glsl", "assets/shaders/simple_frag.glsl")
  // private val staticShader = ShaderProgram("assets/shaders/tron_vert.glsl", "assets/shaders/tron_frag.glsl")
   // private var meshes = arrayListOf<Mesh>()
    private val matrixGround = Renderable()
    private val matrixSphere = Renderable()
    private val tronCamera = TronCamera()




   // private var mode= 0

    //scene setup
    init {

        //initial opengl state
        glClearColor(0.0f, 0.0f, 0.0f, 1.0f); GLError.checkThrow()  //Hintergrundfarbe
        //glEnable(GL_CULL_FACE); GLError.checkThrow() //Cull-Facing wurde aktiviert
        glDisable(GL_CULL_FACE); GLError.checkThrow()  // Cull-Facing wurde deaktiviert
        glFrontFace(GL_CCW); GLError.checkThrow() // Alle Dreiecke, die zur Kamera gerichtet sind, sind entgegen des Uhrzeigersinns definiert.
        glCullFace(GL_BACK); GLError.checkThrow() // Es werden alle Dreiecke verworfen, die nach hinten zeigen
        glEnable(GL_DEPTH_TEST); GLError.checkThrow()
        glDepthFunc(GL_LESS); GLError.checkThrow()

        //Aufgabe 1.2

        // val verticesHouse: FloatArray = floatArrayOf(
        //Position             //Color
        //  -0.5f, -0.5f, 0.0f,     0.0f, 0.0f, 1.0f,
        // 0.5f, -0.5f, 0.0f,     0.0f, 0.0f, 1.0f,
        //   0.5f,  0.5f, 0.0f,     0.0f, 1.0f, 0.0f,
        //  0.0f,  1.0f, 0.0f,     1.0f, 0.0f, 0.0f,
        //    -0.5f,  0.5f, 0.0f,     0.0f, 1.0f, 0.0f
        //   )


        // val indicesHouse = intArrayOf(
        //  0, 1, 2,    //erstes Dreieck
        // 0, 2, 4,    //zweites Dreieck
        //  4, 2, 3    // drittes Dreieck
        //)

        //   val attrPosHouse = VertexAttribute(3, GL_FLOAT, 24, 0)  // Pointer Position
        //  val attrColHouse = VertexAttribute(3, GL_FLOAT,24, 12)  // Pointer Farbe
        //  val attributes = arrayOf(attrPosHouse, attrColHouse)

        //  meshes.add(Mesh(verticesHouse, indicesHouse, attributes))

        //Aufgabe 1.2.4
        // val verticesName: FloatArray = floatArrayOf(
        // Position von A        //Color
        //  -0.75f, -0.25f, 0.0f,  0.0f, 0.0f, 1.0f,
        //  -1.0f, -0.25f, 0.0f,   0.0f, 0.0f, 1.0f,
        //  -0.75f, 0.25f, 0.0f,   0.0f, 0.0f, 1.0f,
        //  -0.5f, 0.25f, 0.0f,    0.0f, 0.0f, 1.0f,
        //  -0.5f, -0.25f, 0.0f,   0.0f, 0.0f, 1.0f,
        //  -0.25f, -0.25f, 0.0f,  0.0f, 0.0f, 1.0f,
        //  -0.25f, 0.75f, 0.0f,   0.0f, 0.0f, 1.0f,
        //  -0.5f, 0.75f, 0.0f,    0.0f, 0.0f, 1.0f,
        //  -0.75f, 0.75f, 0.0f,   0.0f, 0.0f, 1.0f,
        //  -0.75f, 0.61f, 0.0f,   0.0f, 0.0f, 1.0f,
        //  -0.5f, 0.61f, 0.0f,    0.0f, 0.0f, 1.0f,
        //  -0.5f, 0.31f, 0.0f,    0.0f, 0.0f, 1.0f,
        //  -0.75f, 0.31f, 0.0f,   0.0f, 0.0f, 1.0f,
        //  -1.0f, 0.75f, 0.0f,    0.0f, 0.0f, 1.0f,
        // Position von M        //Color
        //  0.25f, -0.25f, 0.0f,   0.0f, 0.0f, 1.0f,
        //   0.5f, -0.25f, 0.0f,    0.0f, 0.0f, 1.0f,
        //   0.25f, 0.5f, 0.0f,     0.0f, 0.0f, 1.0f,
        //   0.5f, 0.5f, 0.0f,      0.0f, 0.0f, 1.0f,
        //   0.61f, 0.25f, 0.0f,    0.0f, 0.0f, 1.0f,
        //   0.75f, 0.5f, 0.0f,     0.0f, 0.0f, 1.0f,
        //   0.75f, -0.25f, 0.0f,   0.0f, 0.0f, 1.0f,
        //   1.0f, -0.25f, 0.0f,    0.0f, 0.0f, 1.0f,
        //   1.0f, 0.5f, 0.0f,      0.0f, 0.0f, 1.0f,
        //   1.0f, 0.75f, 0.0f,     0.0f, 0.0f, 1.0f,
        //  0.25f, 0.75f, 0.0f,    0.0f, 0.0f, 1.0f
        //  )

        //val indicesName = intArrayOf(
        //A
        //  1, 0, 2,
        //  3, 4, 5,
        //  5, 6, 3,
        //   6, 7, 3,
        //   7, 8, 10,
        //   8, 9, 10,
        //   11, 12, 3,
        //   12, 2, 3,
        //  8, 13, 2,
        //   13, 1, 2,
        //M
        // 14, 15, 16,
        //  15, 17, 16,
        //  17, 18, 19,
        //  19, 20, 22,
        //  20, 21, 22,
        //  22, 16, 24,
        //  22, 24, 23,
        //  16, 22, 23,
        //  16, 23, 24
        // )

        // val attrPosName = VertexAttribute(3, GL_FLOAT, 24, 0)
        //val attrColName = VertexAttribute(3, GL_FLOAT, 24, 12)
        // val attributes1 = arrayOf(attrPosName, attrColName)
        // meshes.add(Mesh(verticesName, indicesName, attributes1))


        //Aufgabe 2.1.1
      //  val ground = Matrix4f()
      //  val sphere = Matrix4f()


       //    ground.rotation((90 * Math.PI / 180).toFloat(), Vector3f(1f, 0f,0f))
       //    ground.scale(0.03f)

       //    sphere.scale(0.5f)

        //TODO: OBJLoader benutzen um die Sphere reinzuladen
        val resSphere = OBJLoader.loadOBJ("assets/models/sphere.obj")
        val objSphere = resSphere.objects[0].meshes[0]

        val attrPos = VertexAttribute(3, GL_FLOAT, 32, 0)
        val attrTC = VertexAttribute(2, GL_FLOAT, 32, 12)
        val attrNorm = VertexAttribute(3, GL_FLOAT, 32, 20)

        val vertexAttributesSphere = arrayOf(attrPos, attrTC, attrNorm)

        // OBJLoader benutzen um den ground reinzuladen
        val resGround = OBJLoader.loadOBJ("assets/models/ground.obj")
        val objGround = resGround.objects[0].meshes[0]

        matrixGround.meshes.add(Mesh(objGround.vertexData, objGround.indexData, vertexAttributesSphere))
        matrixSphere.meshes.add(Mesh(objSphere.vertexData, objSphere.indexData, vertexAttributesSphere))
        tronCamera.rotate(Math.toRadians(-20.0f), 0.0f, 0.0f)  //Aufgabe 2.4.2
        tronCamera.translate(Vector3f(0.0f, 0.0f, 4.0f))              //Aufgabe 2.4.2
        tronCamera.parent = matrixSphere


    }

    fun render(dt: Float, t: Float) {
        staticShader.use()
        glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT)
        matrixSphere.render(staticShader)
        matrixGround.render(staticShader)
        tronCamera.bind(staticShader)

    }





    //fun render(dt: Float, t: Float) {
        //glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT)
        //staticShader.use()
       // if(window.getKeyState(GLFW.GLFW_KEY_0)) // Haus
       //     mode = 0
       // if(window.getKeyState(GLFW.GLFW_KEY_1))  // Initialien
       //     mode = 1
       // if(window.getKeyState(GLFW.GLFW_KEY_2))  // Kreis
      //      mode = 2
      //  meshes[mode].render()



    //}



  //Aufgabe 2.5
  fun update(dt: Float, t: Float) {
      if(window.getKeyState(GLFW.GLFW_KEY_W))    //
      {
          matrixSphere.translate(Vector3f(0f,0f,-dt))
      }
      if(window.getKeyState(GLFW.GLFW_KEY_S))    //
      {
          matrixSphere.translate(Vector3f(0f,0f,dt))
      }
      if(window.getKeyState(GLFW.GLFW_KEY_A))    //
      {
          matrixSphere.rotate(0f,dt,0f)
      }
      if(window.getKeyState(GLFW.GLFW_KEY_D))    //
      {
          matrixSphere.rotate(0f,-dt,0f)
      }




  }

    fun onKey(key: Int, scancode: Int, action: Int, mode: Int) {}

    fun onMouseMove(xpos: Double, ypos: Double) {}


    fun cleanup() {}
}


