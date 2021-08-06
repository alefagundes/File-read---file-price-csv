package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.dataProducts;

public class Program {

	public static void main(String[] args) {
		//C:\temp\prod.txt - caminho com o arquivo
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		List<dataProducts> list = new ArrayList<>();
		
		System.out.println("Enter with a file path:");
		String sourceFilePath = sc.nextLine();
		
		File sourceFile = new File(sourceFilePath);
		String sourceFolder = sourceFile.getParent();
		
		boolean sucess = new File(sourceFolder + "\\out").mkdir();
		String targetFile = sourceFolder + "\\out\\summary.csv";
		
		try(BufferedReader br = new BufferedReader(new FileReader(sourceFilePath))){
			
			String line = br.readLine();
			while(line != null) {
				String[] filds = line.split(",");
				String name = filds[0];
				Double price = Double.parseDouble(filds[1]);
				Integer quantity = Integer.parseInt(filds[2]);
				
				list.add(new dataProducts(name, price, quantity));
				line = br.readLine();
			}
			
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(targetFile))){
				for(dataProducts prod : list) {
					bw.write(prod.getName() + "," + String.format("%.2f", prod.total()));
					bw.newLine();
				}
				System.out.println(targetFile + " Created");
			}catch(IOException e) {
				System.out.println("Error writing: " + e.getMessage());
			}
			
			
		}catch(IOException e) {
			System.out.println("Erro reading: " + e.getMessage());
		}
			
	}

}
