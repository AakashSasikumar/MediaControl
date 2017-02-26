import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Aakash on 2/26/2017.
 */
public class VideoCapturer extends SwingWorker<Void, Void> {
    static Mat webCamImage = new Mat();

    @Override
    protected Void doInBackground() throws Exception {
        Image tempImage;
        MainFrame.videoCapture =new VideoCapture(0);
        MainFrame.videoCapture.set(Videoio.CAP_PROP_FRAME_WIDTH, 640);
        MainFrame.videoCapture.set(Videoio.CAP_PROP_FRAME_HEIGHT, 480);
        ImageProcessor imageProcessor = new ImageProcessor();
        if(MainFrame.videoCapture.isOpened()){
            while(true){
                MainFrame.videoCapture.read(webCamImage);

                if(!webCamImage.empty()){
                    Core.flip(webCamImage, webCamImage, 180);
                    webCamImage=imageProcessor.detectFaces(webCamImage);
                    //tempImage=imageProcessor.toBufferedImage(webCamImage);
                    //ImageIcon icon= new ImageIcon(tempImage, "captured");
                    //MainFrame.toShow.setIcon(icon);


                }
                else{
                    break;
                }
            }
        }
        else{
            System.out.println("Couldn't open");
        }
        return null;
    }
}
