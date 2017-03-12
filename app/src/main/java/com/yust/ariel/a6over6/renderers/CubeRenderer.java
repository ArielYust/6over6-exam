package com.yust.ariel.a6over6.renderers;

import android.opengl.GLSurfaceView;

import com.yust.ariel.a6over6.providers.OrientationProvider;
import com.yust.ariel.a6over6.data.Quaternion;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Ariel Yust on 11-Mar-17.
 */
@Deprecated
public class CubeRenderer implements GLSurfaceView.Renderer {
    /**
     * The colour-cube that is drawn repeatedly
     */
    private Cube mCube;
    /**
     * The current provider of the device orientation.
     */
    private OrientationProvider mOrientationProvider = null;
    private Quaternion mQuaternion = new Quaternion();


    /**
     * Initialises a new CubeRenderer
     */
    public CubeRenderer() {
        mCube = new Cube();
    }


    /**
     * Sets the mOrientationProvider of this renderer. Use this method to change which sensor fusion should be currently
     * used for rendering the cube. Simply exchange it with another mOrientationProvider and the cube will be rendered
     * with another approach.
     * 
     * @param mOrientationProvider The new orientation provider that delivers the current orientation of the device
     */
    public void setOrientationProvider(OrientationProvider mOrientationProvider) {
        this.mOrientationProvider = mOrientationProvider;
    }

    /**
     * Perform the actual rendering of the cube for each frame
     * 
     * @param gl The surface on which the cube should be rendered
     */
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();

        if (mOrientationProvider != null) {
            mOrientationProvider.getQuaternion(mQuaternion);

            gl.glRotatef((float) (2.0f * Math.acos(mQuaternion.getW()) * 180.0f / Math.PI),
                    mQuaternion.getX(), mQuaternion.getY(), mQuaternion.getZ());

            float dist = 3;
            drawTranslatedCube(gl, 0, 0, -dist);
            drawTranslatedCube(gl, 0, 0, dist);
            drawTranslatedCube(gl, 0, -dist, 0);
            drawTranslatedCube(gl, 0, dist, 0);
            drawTranslatedCube(gl, -dist, 0, 0);
            drawTranslatedCube(gl, dist, 0, 0);
        }

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
    }

    /**
     * Draws a translated cube
     * 
     * @param gl the surface
     * @param translateX x-translation
     * @param translateY y-translation
     * @param translateZ z-translation
     */
    private void drawTranslatedCube(GL10 gl, float translateX, float translateY, float translateZ) {
        gl.glPushMatrix();
        gl.glTranslatef(translateX, translateY, translateZ);

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

        mCube.draw(gl);
        gl.glPopMatrix();
    }

    /**
     * Update view-port with the new surface
     * 
     * @param gl the surface
     * @param width new width
     * @param height new height
     */
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0, 0, width, height);
        float ratio = (float) width / height;
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glFrustumf(-ratio, ratio, -1, 1, 1, 10);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glDisable(GL10.GL_DITHER);
        gl.glClearColor(0, 0, 0, 1);
    }

    /**
     * Flag indicating whether you want to view inside out, or outside in
     */
    private boolean showCubeInsideOut = true;

    /**
     * Toggles whether the cube will be shown inside-out or outside in.
     */
    public void toggleShowCubeInsideOut() {
        this.showCubeInsideOut = !showCubeInsideOut;
    }
}
