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
	
	public static void main(String[] args) throws IOException, DataFormatException {
		

		String finalString3 = CodecUtil.decompress(CodecUtil
				.decode("eJx9VsFy4jgQvecrUj6vKRsbA7klGWaXrSRDAdnda1tqgyqypJFkZjNT+feVASeMpNkT0K8lPfV73eLH1fV1skegqJPrm+sf7qcLULLqas7Mfsta7OOi4/y3AbvXCBYHKJ8U2XSWFcX0PWHdiSUNlv0p60h0q9luh3r7qsKDIunLlRcSBNf4tUNjg+zW7CLbGmbxlJrUWDWYT6t0TKtJWmb1PIW6atJ84urhYrPJmN48b5LzSql3x2UTUlO3YJxmJS3SMs/zdDapi3RWVJRMmjKHsrn5vPxns10vbh8Tt/it3yFRWipzUeUDCiq1T1pS5F5McbCN1O0vwpFLHlAbJoVfPjwwMly+rMZFVcIkrXBK05JmdTrPZtN0NgOsYU7qsiyGm3NJwEb3e4gjoH7XslNh/sA1uf/yuHreLpIPaNPVMblQM+BPXVujX6rmK/XP7UMPzFj/YNkCE08QmHkvjR3CiXUmcjwGSsz8xbTtwFfDuZ/soeYYOagT/4e2IMB53fjsmCHSKYZ0qWLkL+BbSo9VSPLpeJRXo/GonCc/7d6isEu/8Aq0C/vnnqJ/nEvgo4R3xoZklcbDpwsfBfZaS+5X+XRQkH9oI4o0EPj8BVHdcnbAjQXb+YREuIeVFvhGAfEBjb0PmNjFQBPbncsdI8DvXAe8PKDY2X2ozkvEtrve/k/oaynQrqT0HUXA0WH21TeTQXr/ayh2BwoW1kg7EulJoxCDEQmia4DYTgfdBRSU0z9QTTn+ofTayRMpgxt5PZlABo5g/Nxv33zGvBN/B0GtfH9oYPQBD8HYpEwjsTK8AkVDNFORGrFjh6HxbVD3+m/Yd5+zOM4l2Rxxf5F5bV2nRLr9xdzLTvhVUVLbKHBWIjoDTW+CZxMI2xvhs3sb3EMdpfyluYtRbjRiFOhZx0a60sRGjv+YRSuIFIbI9tP7VOvBbOiE/q/FhSzvb2d/n4uns0WrGbl4THvtmn8fw7ADFnaPWpx68SPsADwDmY8cPUoOC63laQBmozPDn/C7V3sam8UoH8+rfF7Ox9NFnl1kvl35306fb4PFXd/0OzTADfa3vXr7DyRLpPI="));
		System.out.println(finalString3);
		
		List<String> test = new ArrayList<>();
		
		String result = getCSId(test);
		//System.out.println(result);

	}


}
