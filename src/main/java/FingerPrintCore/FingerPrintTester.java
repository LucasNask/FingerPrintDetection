package FingerPrintCore;

import org.bytedeco.opencv.opencv_core.Mat;
import static org.bytedeco.opencv.global.opencv_imgcodecs.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;

import Util.ImageUtils;

public class FingerPrintTester {
	public static void main(String[] args) throws IOException {
		
		System.out.println("Iniciando reconhecimento de digital, muitas janelas ser�o abertas com as fotos das digitais de cada processo, boa sorte para nos e que Deus nos ajude.");
		System.out.println(new Date());
		
		FingerPrint finger = new FingerPrint();
		ImageUtils imageUtils = new ImageUtils();
		
		
		
		//Setando a 1 imagem na classe
		Mat imagem = imread("src/resources/fingerprint.png");
		finger.setImagemColorida(imagem);
		
		//printando a imagem original
		System.out.println("Printando imagem normal---");
		imageUtils.displayImage(imageUtils.Mat2BufferedImage(finger.getImagemColorida()));
		
		
		//Binariza��o
		finger.binarization();
		System.out.println("Printando imagem binarizada---");
		imageUtils.displayImage(imageUtils.Mat2BufferedImage(finger.getInputBinary()));
		
		//Skeletiza��o
		System.out.println("Printando imagem Skeletizada--");
		finger.skeleton(finger.getInputBinary());
		imageUtils.displayImage(imageUtils.Mat2BufferedImage(finger.getInputBinary()));
		
		
		
	}
}
