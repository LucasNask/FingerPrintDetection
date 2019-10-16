package FingerPrintCore;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class FingerPrint {

    private Mat imagemColorida = new Mat();
    private Mat imagemColorida2 = new Mat();
    private Mat inputBinary = new Mat();
    private Mat skeleton = new Mat();

  
    public void binarization(){
    	
    	double threshold = Imgproc.threshold(this.getImagemColorida() , this.getInputBinary() , 0,255, Imgproc.THRESH_BINARY);
        
    }
    
    public void skeleton(Mat _img)
    {
        boolean done = false;
        Mat img = _img.clone();
    //    CvUtilsFX.showImage(img, "tresh_1");
        

        Mat imgGray = new Mat();
        Imgproc.cvtColor(img, imgGray, Imgproc.COLOR_BGR2GRAY);

        Mat tresh = new Mat();
        double thresh = Imgproc.threshold(imgGray, tresh, 100, 255, Imgproc.THRESH_BINARY_INV | Imgproc.THRESH_OTSU); 
       // CvUtilsFX.showImage(tresh, "tresh_1");

        Mat element = Imgproc.getStructuringElement(Imgproc.MORPH_CROSS, new Size(3,3));
        Mat eroded = new Mat();
        Mat temp = new Mat();
        Mat skel = new Mat (tresh.rows(), tresh.cols(), CvType.CV_8UC1, new Scalar (0));

        int size = _img.cols() * _img.rows();
        int zeros = 0;

        while(!done)
        {
            Imgproc.erode(tresh, eroded, element);
            Imgproc.dilate(eroded, temp, element);
            Core.subtract(tresh, temp, temp);
            Core.bitwise_or(skel, temp, skel);
            eroded.copyTo(tresh);

            zeros = size - Core.countNonZero(tresh);
            if(zeros == size)
                done = true;
        }
        //imageUtils.displayImage(imageUtils.Mat2BufferedImage(skel));
        //CvUtilsFX.showImage(skel, "Skeleton");
    }
    
    

	public Mat getInputBinary() {
		return inputBinary;
	}

	public void setInputBinary(Mat inputBinary) {
		this.inputBinary = inputBinary;
	}
	public void setInputBinary(byte[] inputBinary) {
		this.inputBinary.put(0, 0, inputBinary);
	}

	public Mat getImagemColorida() {
		return imagemColorida;
	}

	public void setImagemColorida(Mat imagemColorida) {
		this.imagemColorida = imagemColorida;
	}
	public void setImagemColorida(byte[] imagemColorida) {
		this.imagemColorida.put(0, 0, imagemColorida);
	}

	public Mat getImagemColorida2() {
		return imagemColorida2;
	}

	public void setImagemColorida2(Mat imagemColorida2) {
		this.imagemColorida2 = imagemColorida2;
	}
    
	public void setImagemColorida2(byte[] imagemColorida2) {
		this.imagemColorida.put(0, 0, imagemColorida2);
	}

	public Mat getSkeleton() {
		return skeleton;
	}

	public void setSkeleton(Mat skeleton) {
		this.skeleton = skeleton;
	}
   
	public void setSkeleton(byte[] Skeleton) {
		this.imagemColorida.put(0, 0, Skeleton);
	}
    
	
}
