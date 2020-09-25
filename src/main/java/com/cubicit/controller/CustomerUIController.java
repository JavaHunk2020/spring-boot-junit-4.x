package com.cubicit.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cubicit.controller.vo.CustomerVO;
import com.cubicit.service.CustomerServiceImpl;

@Controller
public class CustomerUIController {
	
	@Autowired
	private CustomerServiceImpl customerServiceImpl;
	
	//Only admin should view the page
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/customers")
	public String showCustomer(){
		return "customers";
	}
	
	
	private  String generateCreditCardNumber() {
		Random random = new Random();
		StringBuilder number = new StringBuilder();
		number.append(String.format("%04d", random.nextInt(10000)));
		number.append(String.format("%04d", random.nextInt(10000)));
		number.append(String.format("%04d", random.nextInt(10000)));
		number.append(String.format("%04d", random.nextInt(10000)));
		return number.toString();
	}

	private String generateCreditCardExpireDate() {
		Random random = new Random();
		int validity=random.nextInt(20);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
		LocalDate date = LocalDate.now();
		date = date.plusYears(validity);
		return formatter.format(date);
	}
	
	private String generateCCVNumber() {
		Random random = new Random();
		StringBuilder number = new StringBuilder();
		number.append(String.format("%03d", random.nextInt(1000)));
		return number.toString();
	}
	
	
	private byte[] processCreditCard(String name){
		byte[] tempa=null;
		Resource resource = new ClassPathResource("images/credit-card-front-template.jpg");
		String cardNumber=generateCreditCardNumber();
		String exp=generateCreditCardExpireDate();
		try {
			InputStream resourceInputStream = resource.getInputStream();
			
			Image src = ImageIO.read(resourceInputStream);

			int wideth = src.getWidth(null);
			int height = src.getHeight(null);

			BufferedImage tag = new BufferedImage(wideth, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = tag.createGraphics();

			g.setBackground(new Color(200, 250, 200));
			g.clearRect(0, 0, wideth, height);
			g.setColor(Color.WHITE);
			g.drawImage(src, 0, 0, wideth, height, null);
			
			// credit card number
			g.setFont(new Font("Lucida Console", Font.BOLD, 36));
			g.drawString(cardNumber.substring(0, 4), 40, 207);
			g.drawString(cardNumber.substring(4, 8), 150, 207);
			g.drawString(cardNumber.substring(8, 12), 260, 207);
			g.drawString(cardNumber.substring(12, 16), 370, 207);
			
			// exp date
			g.setFont(new Font("Lucida Console", Font.PLAIN, 24));
			g.drawString(exp, 65, 250);

			// customer name
			g.setFont(new Font("Tahoma", Font.PLAIN, 28));
			g.drawString(name.toUpperCase(), 30, 290);
			
			//cardType
			g.setFont(new Font("Lucida Console",Font.ITALIC,20));
			g.drawString("VISA", 120, 20);
			
			//load new image
			Resource simage = new ClassPathResource("images/logo.png");
			InputStream simageInputStream = simage.getInputStream();
			Image img = ImageIO.read(simageInputStream);
			//Draw image on given card
			g.drawImage(img, 304, 255, 91, 45, null);

			g.dispose();

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(tag, "jpg", baos);
			baos.flush();
			tempa= baos.toByteArray();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return tempa;
	}
	
	//<img src="loadPhoto?dbid=12"/>
	@GetMapping({"/loadCrediCard"})
	public void renderCrediCard(@RequestParam int cid,@RequestParam String name,
			HttpServletResponse resp) throws IOException{
			byte[] photo=processCreditCard(name);
			resp.setContentType("image/png");
			ServletOutputStream outputStream=resp.getOutputStream(); //reference of the body of the response
			if(photo!=null){
				outputStream.write(photo);
				outputStream.flush();
				outputStream.close();	
			}
	}
	
	@GetMapping(value="/customer/photo")
	public void customerPhoto(@RequestParam int cid,HttpServletResponse resp)
			throws IOException {
		CustomerVO customerVO=customerServiceImpl.findById(cid);
		byte[] photo = {};
		if(customerVO.getPhoto()!=null && customerVO.getPhoto().length>2){
			photo= customerVO.getPhoto();
		}
		resp.setContentType("image/png");
		ServletOutputStream outputStream = resp.getOutputStream(); 
		if (photo != null) {
			outputStream.write(photo);
			outputStream.flush();
			outputStream.close();
		}
	}

}
