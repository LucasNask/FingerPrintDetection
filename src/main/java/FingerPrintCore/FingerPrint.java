package FingerPrintCore;

import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatOp;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.Size;

import Util.ImageUtils;

import static org.bytedeco.opencv.global.opencv_core.*;
import static org.bytedeco.opencv.global.opencv_imgproc.*;

public class FingerPrint {

    private Mat imagemColorida = new Mat();
    private Mat imagemColorida2 = new Mat();
    private Mat inputBinary = new Mat();
    private Mat skeleton = new Mat();
    private ImageUtils imageUtils = new ImageUtils();

  
    public void binarization(){
    	
    	cvtColor(imagemColorida, imagemColorida2, COLOR_BGR2GRAY);
    	double threshold = threshold(this.getImagemColorida2() , this.getInputBinary() , 0,255, CV_THRESH_BINARY | CV_THRESH_OTSU);
        
    }
    
    public void skeleton(Mat _img)
    {
        boolean done = false;
        Mat img = _img.clone();
    //    CvUtilsFX.showImage(img, "tresh_1");
        

        Mat imgGray = new Mat();
        cvtColor(img, imgGray, COLOR_BGR2GRAY);

        Mat tresh = new Mat();
        double thresh = threshold(imgGray, tresh, 100, 255, THRESH_BINARY_INV | THRESH_OTSU); 
       // CvUtilsFX.showImage(tresh, "tresh_1");

        Mat element = getStructuringElement(MORPH_CROSS, new Size(3,3));
        Mat eroded = new Mat();
        Mat temp = new Mat();
        Mat skel = new Mat (tresh.rows(), tresh.cols(), CV_8UC1, new Scalar (0));

        int size = _img.cols() * _img.rows();
        int zeros = 0;

        while(!done)
        {
            erode(tresh, eroded, element);
            dilate(eroded, temp, element);
            subtract(tresh, temp, temp);
            bitwise_or(skel, temp, skel);
            eroded.copyTo(tresh);

            zeros = size - countNonZero(tresh);
            if(zeros == size)
                done = true;
        }
        //imageUtils.displayImage(imageUtils.Mat2BufferedImage(skel));
        imageUtils.show(skel, "Skelletizada");
        //CvUtilsFX.showImage(skel, "Skeleton");
    }
    
    

	public Mat getInputBinary() {
		return inputBinary;
	}

	public void setInputBinary(Mat inputBinary) {
		this.inputBinary = inputBinary;
	}

	public Mat getImagemColorida() {
		return imagemColorida;
	}

	public void setImagemColorida(Mat imagemColorida) {
		this.imagemColorida = imagemColorida;
	}

	public Mat getImagemColorida2() {
		return imagemColorida2;
	}

	public void setImagemColorida2(Mat imagemColorida2) {
		this.imagemColorida2 = imagemColorida2;
	}

	public Mat getSkeleton() {
		return skeleton;
	}

	public void setSkeleton(Mat skeleton) {
		this.skeleton = skeleton;
	}
   
    
	
}
