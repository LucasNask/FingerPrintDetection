package FingerPrintCore;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;

import Util.ImageUtils;

public class FingerPrintTester {
	public static void main(String[] args) throws IOException {
		
		System.out.println("Iniciando reconhecimento de digital, muitas janelas serão abertas com as fotos das digitais de cada processo, boa sorte para nos e que Deus nos ajude.");
		System.out.println(new Date());
		
		FingerPrint finger = new FingerPrint();
		ImageUtils imageUtils = new ImageUtils();
		
		
		
		//Setando a 1 imagem na classe
		File file = new File("E:\\Documentos\\B1\\101_1.tif");
		BufferedImage image = ImageIO.read(file);
		byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
		
		finger.setImagemColorida(pixels);
		
		//printando a imagem original
		System.out.println("Printando imagem normal---");
		imageUtils.displayImage(imageUtils.Mat2BufferedImage(finger.getImagemColorida()));
		
		
		//Binarização
		finger.binarization();
		System.out.println("Printando imagem binarizada---");
		imageUtils.displayImage(imageUtils.Mat2BufferedImage(finger.getInputBinary()));
		
		//Skeletização
		System.out.println("Printando imagem Skeletizada--");
		finger.skeleton(finger.getInputBinary());
		imageUtils.displayImage(imageUtils.Mat2BufferedImage(finger.getInputBinary()));
		
		
		
		
		
		
		
	}
}
