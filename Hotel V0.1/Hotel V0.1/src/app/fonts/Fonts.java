package app.fonts;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

public class Fonts {
	
	public Font poppins (float font_size) {
		InputStream is = Fonts.class.getResourceAsStream("/app/fonts/Poppins-Regular.ttf");
		Font POPPINS = null;
		try {
			POPPINS = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(font_size);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		} 
		return POPPINS;
	}
	
	public static Font mulish (float font_size) {
		InputStream is = Fonts.class.getResourceAsStream("/app/fonts/Mulish-Bold.ttf");
		Font MULISH = null;
		try {
			MULISH = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(font_size);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		} 
		return MULISH;
	}
	
	public Font poppinsMedium (float font_size) {
		InputStream is = Fonts.class.getResourceAsStream("/app/fonts/Poppins-Medium.ttf");
		Font POPPINS = null;
		try {
			POPPINS = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(font_size);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		} 
		return POPPINS;
	}
	
}
