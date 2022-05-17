package animenews.Utils;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.io.IOException;

public class FileUtilsService implements WebMvcConfigurer {

    public static String uploadFile(MultipartFile file, String type) throws IOException {
//        String fileName = file.getOriginalFilename().replaceAll("\\s{2,}", " ");
//        StringBuffer folder = new StringBuffer();
//        switch (type) {
//            case "character":
//                folder.append(WebResourceConfigure.ANIME_FOLDER).append("/chars/");
//                break;
//            case "character_thumbs":
//                folder.append(WebResourceConfigure.ANIME_FOLDER).append("/chars/thumbs/");
//                break;
//            case "anime":
//                folder.append(WebResourceConfigure.ANIME_FOLDER).append("/animes/");
//                break;
//            case "studio":
//                break;
//            default: // for post
//                int year = Calendar.getInstance().get(Calendar.YEAR);
//                int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
//                folder.append(WebResourceConfigure.POST_FOLDER)
//                        .append("/").append(year)
//                        .append("/").append(month)
//                        .append("/");
//                break;
//        }
//        File refreshFolder = new File(folder.toString());
//        if (!refreshFolder.exists())
//            refreshFolder.mkdirs();
//        String uploadFile = folder + fileName.toString();
//        int i = 0;
//        while (true) {
//            if (!new File(uploadFile).exists())
//                break;
//            else
//                uploadFile = folder + String.valueOf(i++).concat("-").concat(fileName);
//        }
//        file.transferTo(new File(uploadFile.toString()));
//
//        uploadFile = uploadFile.replace(WebResourceConfigure.ROOT_CONTENT_SYS, "");
//        System.out.println("uploadFile");
//        System.out.println(uploadFile);
//        return WebResourceConfigure.HOST + uploadFile;
        return null;
    }

}
