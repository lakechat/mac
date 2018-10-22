package com.codec;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;



public class CodecUtil {
    


	public static String encode(byte[] byteArray) {

		return Base64.getEncoder().encodeToString(byteArray);

	}

	public static byte[] decode(String encodedString) {

		return Base64.getDecoder().decode(encodedString);

	}

//	public static byte[] compress(String stringMessage) {
//		byte[] output = null;
//		try {
//			Deflater deflater = new Deflater();
//			output = new byte[Short.MAX_VALUE];
//			deflater.setInput(stringMessage.getBytes("UTF-8"));
//			deflater.finish();
//			deflater.deflate(output);
//		} catch (UnsupportedEncodingException e) {
//			if(logger.isDebugEnabled())logger.debug(e.getMessage(), e);
//		}
//		return output;
//	}
//
	// public static String decompress(byte[] byteArray) {
	// String str = null;
	// try {
	// Inflater inflater = new Inflater();
	// inflater.setInput(byteArray);
	//
	// byte[] result = new byte[Short.MAX_VALUE];
	// int len = inflater.inflate(result);
	// inflater.end();
	// str = new String(result, 0, len, "UTF-8");
	// } catch (DataFormatException e) {
	// if(logger.isDebugEnabled())logger.debug(e.getMessage(), e);
	// } catch (UnsupportedEncodingException e) {
	// if(logger.isDebugEnabled())logger.debug(e.getMessage(), e);
	// }
	// return str;
	// }
	//
	public static byte[] compress(String stringMessage) throws IOException {
		Deflater deflater = new Deflater();
		byte[] input = stringMessage.getBytes("UTF-8");
		deflater.setInput(input);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(
				input.length);

		deflater.finish();
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer); // returns the generated
													// code... index
			outputStream.write(buffer, 0, count);
		}
		outputStream.close();
		byte[] output = outputStream.toByteArray();

		// LOG.debug("Original: " + data.length / 1024 + " Kb");
		// LOG.debug("Compressed: " + output.length / 1024 + " Kb");
		return output;
	}

	public static String decompress(byte[] data) throws IOException,
			DataFormatException {
		Inflater inflater = new Inflater();
		inflater.setInput(data);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(
				data.length);
		byte[] buffer = new byte[1024];
		while (!inflater.finished()) {
			int count = inflater.inflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		outputStream.close();
		String output = outputStream.toString("UTF-8");

		// LOG.debug("Original: " + data.length);
		// LOG.debug("Compressed: " + output.length);
		return output;
	}

	public static String encryptJson(String jsonMessage) throws IOException {
		String returnVal = "{\"header\":{\"contentType\":\"content/encrypted\"},\"content\":\"" + CodecUtil.encode(CodecUtil.compress(jsonMessage)) + "\"}";
		System.out.println(returnVal);
		return returnVal;
	}
	
	public static String getCSId(List<String> idList){
		String result = null;
		if(idList!=null && idList.size()>0){
			StringBuilder sb = new StringBuilder();
			int length = idList.size();
			for(int i=0; i<length; i++){
				sb.append(idList.get(i));
				if(i<(length-1))
					sb.append(",");
			}
			result = sb.toString();
		}
		return result;
	}
	
	public static String encryptPassword(String input) {
	        if (null != input && !input.isEmpty()) {
	            try {
	                return CodecUtil.encode(CodecUtil.compress(input));
	            } catch (IOException e) {
	               
	            }
	        }
	        return input;
	    }
	
	public static void main(String[] args) throws IOException, DataFormatException {
		

		String finalString3 = CodecUtil.decompress(CodecUtil
				.decode("eJyFU8tu2zAQvOcrCJ0rQS/Hlm+G4wIqkia1nKJAUwQUubRZyKTCh9MgyL+XkkPFTlz0JHt3dnZ3hvt8hlCwAUxBBWiKnt1fF6DkxtYN15sV30IXF7ZpPvncXAE24FPJKCtGcZyleZ7GA2ZpRUm7dBAmwRD9IusT0ZXi6zWo1VPbEwYHqT36qHt58y4kCCzhwYI2H7i3ej2wruYzH9bcwCuWsSJhRYbDAgoa5gVOw6KAPByzPGN0kiRxSqe3la+Uat2XjbJ8wlKahIzkLMwJjENMMYQknaR0lNdZnePp5/JHtVouZleBK37pGIJWyVYfCM3bGaVqv/U4jZLzKI3ywnfDjAExQK/r3+7box65SM65fmgit/EfHBG5PY2+Li/6ArwGYe612Lb3RuF2kEBaRcCLk8RH4a94b21QOfrZTTmXQjhKqQZjYMeJl9AtzlJMSFiPWBrmbAJhnWRJCGOck1EWxxQmvnAjtRnYjbPMMQ0jgepY+5Sl24Nela39pAe+t1i51T6+kH62dyWDARQbfKC/hh0obp660NjzKlnjuoE5tno/zWLnGqHyYoryNEkihEqhDXbvboququrbZbVYfl8s78SdeBtaE8Vbw6U4zXDxBpgiK9z5IbMBRKzqlkIa3C83FyJSGPhjomPferpXg32G6SUQ4Dug3WVWBm9bf555McrSIh4X/izAKP7xtoQ3pl8KpXFy7haUgjvnuVijR6yE+/qGO9zYHh9H/vmslbTt0VT8VakT/mHjpqit2fekwLBtjK+zrusRkdko0BvZ0Nu2BXXJt/v8W+8BcCkfTwIwpaVgsgv+RL+8alxpc0163em/9Grw/zGkAayOlPfv7uzlLzEyeDI="));
		
		String pass = "admin:FixStream123!";
		//String finalString3 = encode(pass.getBytes());
		System.out.println(finalString3);
		String s = decompress(Base64.getDecoder().decode("eJxzy6wILilKTcw1NDJWBAAhnARL"));
		System.out.println(s);
		
		
	}


}
