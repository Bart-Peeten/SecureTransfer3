package Services.services.stegano;

public class BasicDecoder /*implements Decoder*/{
/*
	public String decode(Image image) {
		int width = (int) image.getWidth();
		int height = (int) image.getHeight();
		
		System.out.println(image.getWidth());
		System.out.println(height);

		PixelReader reader = image.getPixelReader();
		
		boolean[] bits = new boolean[width*height];
		
		IntStream.range(0,height*width)
		.mapToObj(i -> new Pair<>(i ,reader.getArgb(i % width, i / width)))
		.forEach(pair -> {
			String binary = Integer.toBinaryString(pair.getValue());
			bits[pair.getKey()] = binary.charAt(binary.length() - 1) == '1';
		});
		
		//decode length
		int length = 0;
		System.out.println(bits.length);
		for (int i = 1; i < 32; i++) {
			if(bits[i]){
				
				length |= (1 << (31 - i));
				System.out.println(i + " " + bits[i] + " " + length);
			}
		};
		
		//length = bits.length/8-4;
		System.out.println(length);
		byte[] data = new byte[length];
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < 8; j++) {
				if(bits[32 + (i * 8) + j]){
					System.out.println(32 + (i * 8) + j);
					data[i] |= (1 << (7 - j));
				}
				
			}
		}
		
		return new String(data);
	}
*/

}
