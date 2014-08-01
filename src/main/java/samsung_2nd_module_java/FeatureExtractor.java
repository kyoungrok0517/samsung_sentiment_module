package samsung_2nd_module_java;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FeatureExtractor {
	public static final String DATA_DIR = "data";
	public static final String DATA_POS = "sample_pos_tagging.txt";
	public static final String DATA_TARGET = "sample_senti_target.txt";
	public static final String DATA_SENTI = "sample_sentiment.txt";
	
	// 정규표현식
	private static final String SENT_PATTERN_STRING = "\\b(\\w+/NN\\s?)+\\w+/VB[PZ]? (\\w+/RB\\s)?\\w+/JJ(\\s*,/, (\\w+/RB\\s)?\\w+/JJ(\\s,/,)?)*(\\s\\w+/CC (\\w+/RB\\s)?\\w+/JJ)?\\s";
	private static final Pattern SENT_PATTERN = Pattern.compile(SENT_PATTERN_STRING);
	

	public FeatureExtractor() {
		
	}

	public void test() throws IOException {
		Path dir = Paths.get(DATA_DIR);
		Path dataFile = dir.resolve(DATA_POS);
		try (BufferedReader reader = Files.newBufferedReader(dataFile,
				StandardCharsets.UTF_8)) {
			String line = "";
			while ((line = reader.readLine()) != null) {
				line = line.trim();

				// 문장이 비어있지 않을 때에만 처리
				if (!line.isEmpty()) {
					Matcher matcher = SENT_PATTERN.matcher(line);
					
					while (matcher.find()) {
						System.out.println(matcher.group());
					}
				}
			}
		}
	}

	private void listDataDir() {
		try {
			Path dir = Paths.get(DATA_DIR);
			DirectoryStream<Path> stream = Files.newDirectoryStream(dir,
					"*.txt");
			for (Path entry : stream) {
				System.out.println(entry.getFileName());
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}