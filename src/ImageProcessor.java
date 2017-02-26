import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

/**
 * Created by Aakash on 2/26/2017.
 */
public class ImageProcessor {
    boolean faceExist=true, negativeClick=false;

    public Mat detectFaces(Mat image) throws AWTException {
        CascadeClassifier faceDetector = new CascadeClassifier("xml/lbpcascade_frontalface.xml");
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(image, faceDetections);
        Rect[] facesArray = faceDetections.toArray();
        //System.out.println(facesArray.length);
        //System.out.println(MainFrame.faceExist);

        if(facesArray.length==0){
            faceExist=false;
            if(negativeClick==false) {
                clickSpace();
            }
            negativeClick=true;
        }
        if(facesArray.length>0&&faceExist==false){
            clickSpace();
            faceExist=true;
            negativeClick=false;

        }
        if(faceExist==true) {
            for (int i = 0; i < facesArray.length; i++) {
                Imgproc.rectangle(image, facesArray[i].tl(), facesArray[i].br(), new Scalar(0, 255, 0), 2);

            }
        }
        if(faceExist==false){
            return image;
        }
        return image;
    }
    public Image toBufferedImage(Mat matrix){
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if ( matrix.channels() > 1 ) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        int bufferSize = matrix.channels()*matrix.cols()*matrix.rows();
        byte [] buffer = new byte[bufferSize];
        matrix.get(0,0,buffer); // get all the pixels
        BufferedImage image = new BufferedImage(matrix.cols(),matrix.
                rows(), type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().
                getDataBuffer()).getData();
        System.arraycopy(buffer, 0, targetPixels, 0, buffer.length);
        return image;
    }
    public void clickSpace() throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_SPACE);



    }
}
