package edu.scoalainformala.stanescu_alexandru_emanuel.Util;

import edu.scoalainformala.stanescu_alexandru_emanuel.Listing.Apartment.Apartment;
import edu.scoalainformala.stanescu_alexandru_emanuel.Listing.Apartment.ApartmentRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;


public class FileUploadUtil {

    public static void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (var inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
    }

    public static final int BITE_SIZE = 4 * 1024;

    public static byte[] compressImage(byte[] data) throws IOException {
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[BITE_SIZE];

        while(!deflater.finished()) {
            int size = deflater.deflate(tmp);
            outputStream.write(tmp,0, size);
        }

        outputStream.close();

        return outputStream.toByteArray();
    }

    public static byte[] decompressImage(byte[] data) throws DataFormatException, IOException {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[BITE_SIZE];

        while (!inflater.finished()) {
            int count = inflater.inflate(tmp);
            outputStream.write(tmp, 0, count);
        }

        outputStream.close();

        return outputStream.toByteArray();
    }

}
