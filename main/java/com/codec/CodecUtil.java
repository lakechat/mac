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
				.decode("eJyVVF1v2jAU/SsoT5s0Mzvki74hoBsrDYyPtlNVIcd2wFoSZ7HDVlX899lOoNDuZQ9I+F7fc+859zgvzo5hyirnqvPiUDKsGFZsxXOmA44LUYSg67su6kPkfOroG4u6mFCT9JvzN5E0555nI3XN7THqEYQxQQBFxAVegAKAWQABDTH1XMiCBBFzX1TbGNt2RZ1lFnJV8e2WVavn0k5xMx7PN4Pp5G7cdGzajQarwWY4m07Hw9VssZmMmuS8TjIud/9kEJorkivWIKQh7eMUIhBGyANe6kMQkb4+Bi7tB1FEUkSv1stj0eWUBWEL9qtmUjVgr0Nu/gM3l9sjy9FkOZzdjRc/Phuoz+d85yaPQreLgq7f7XmtbCaKU58lEEag5yMIPEQgwBT2AfQZo7jnhkmIr64nD8vVYjy4dQ4GECv8yqOsRCnt8nNcYK26PZh7e/2fi8J0Cbvwg/sxRh/QRzsS23PCrnHOs2eTjtmfWnZ8CKHJageRHU4yNuVS6fSjE98O5s6TTu2EVK2MTiqBH4QusLbicpiJmp6NhStWqK/tfTPT41PTeVknR8mWv7kiO1NfZlilosrbzCu3PTcceLFdKu3r1xQvpMJ6h+vGrceCFsaSegAzuyPKJRFaDEYn5YDS6mwZLuy6vndGmmdcPZtWtRkZNRNPBcGqUbLtk2Lboz1JVhk9W2GOPHVkITLLcx3fxLP72PTJ3mP9osVJ6aOoe152edFN+R+p9Gh5l4jcbkDWiSQVLw3G5Iz5Pr+0954VVFiqQ+NL0xqXXypRl2cayjteqRpnOgRNTcHUJUqzRXnJqnkvONJvhDAIgj4NgdcPQtBH1AMsoFGKfOYhzzrjfOBLcJLVUjV+teiNf3PdcI7Jz1NYLy8fnVbYjkpFjvkRz3mnlPmMFW99/OLc304239f6iZqiuahUpxCqI0pWmAqTXX4dT6fvs4dmyxxncZ0n9mPrXM+GKOrBBRpZA5k1XjwL8wuAC8H29/vptJLHRxCPV/ezxY39mgjKMhvzb8DQwKznJv6TsXKQ8T07OfPljYO1Vw9vrH7y1OXNp8PhLyVa1Q8="));
		
		String pass = "admin:FixStream123!";
		//String finalString3 = encode(pass.getBytes());
		System.out.println(finalString3);
		String s = decompress(Base64.getDecoder().decode("eJxzy6wILilKTcw1NDJWBAAhnARL"));
		System.out.println(s);
		
		
	}


}
