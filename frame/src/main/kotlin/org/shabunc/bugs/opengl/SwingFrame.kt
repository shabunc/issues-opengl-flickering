package org.shabunc.bugs.opengl

import com.jogamp.opengl.GLAutoDrawable
import com.jogamp.opengl.GLCapabilities
import com.jogamp.opengl.GLEventListener
import com.jogamp.opengl.GLProfile
import com.jogamp.opengl.awt.GLCanvas
import com.jogamp.opengl.util.FPSAnimator
import java.awt.BorderLayout
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.JFrame
import kotlin.system.exitProcess
import com.jogamp.opengl.GL2


private fun createGlPanel(): GLCanvas {
    val profile = GLProfile.get(GLProfile.GL2)
    val capabilities = GLCapabilities(profile)
    capabilities.sampleBuffers = true
    capabilities.doubleBuffered = true
    return GLCanvas(capabilities)
}

private class SwingFrame : JFrame() {
    val canvas = createGlPanel()

    init {
        addWindowListener(object : WindowAdapter() {
            override fun windowClosing(evt: WindowEvent) {
                dispose()
                exitProcess(0)
            }
        })
        contentPane.add(canvas, BorderLayout.CENTER)
        setSize(600, 500)
        isVisible = false
    }
}

fun initApp() {
    val frame = SwingFrame()
    val canvas = frame.canvas

    canvas.addGLEventListener(object : GLEventListener {
        override fun reshape(glautodrawable: GLAutoDrawable, x: Int, y: Int, width: Int, height: Int) {
        }

        override fun init(glautodrawable: GLAutoDrawable) {
            val gl = glautodrawable.gl.gL2
            gl.glClearColor(1f, 0f, 0.6f, 1f)
            val animator = FPSAnimator(glautodrawable, 60, false)
            animator.start()
        }

        override fun display(glautodrawable: GLAutoDrawable) {
            glautodrawable.gl.gL2.glClear(GL2.GL_COLOR_BUFFER_BIT or GL2.GL_DEPTH_BUFFER_BIT)
        }

        override fun dispose(glautodrawable: GLAutoDrawable) {}
    })

    frame.isVisible = true
}

fun main() {
    initApp()
}
