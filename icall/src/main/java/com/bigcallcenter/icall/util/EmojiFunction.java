package com.bigcallcenter.icall.util;

import com.bigcallcenter.icall.util.EmojiConverter.Type;



public class EmojiFunction {
	private static EmojiConverter converter;
	private static String imgpath="/static/emoji/unicode/";
	static{
    	//取得bean对象  
		 converter = new EmojiConverter.Builder()  
	        .from(Type.UNICODE)  
	        .to(Type.SOFTBANK)  
	        .build();  
	}

	public static String emoji(String rootPath,String arg){
		return emojiFun(20,20,rootPath,arg);
	}
	public static String emojiFun(Integer width,Integer height,String rootPath,String arg){
		if(width==null){
			width=20;
		}
		if(height==null){
			height=20;
		}
		String ss= converter.convert(width.intValue(),height.intValue(),rootPath+imgpath,arg);
		return ss;
	}
}
