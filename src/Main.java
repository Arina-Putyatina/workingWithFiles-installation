import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) {

        String mainFolder = "D:\\\\Games";

        File folderSrc = createFolder(mainFolder, "src");
        if (folderSrc == null) return;
        File folderRes = createFolder(mainFolder, "res");
        if (folderRes == null) return;
        File folderSavegames = createFolder(mainFolder, "savegames");
        File folderTemp = createFolder(mainFolder, "temp");
        if (folderTemp == null) return;

        File folderMain = createFolderInFolder(folderSrc, "main");
        if (folderMain == null) return;
        File folderTest = createFolderInFolder(folderSrc, "test");

        File fileMain = createFile(folderMain, "Main.java");
        File fileUtils = createFile(folderMain, "Utils.java");

        File folderDrawables = createFolderInFolder(folderRes, "drawables");
        File folderVectors = createFolderInFolder(folderRes, "vectors");
        File folderIcons = createFolderInFolder(folderRes, "icons");

        File fileTemp = createFile(folderTemp, "temp.txt");

        if (fileTemp != null) {
            String log = stringBuilder.toString();
            byte[] buffer = log.getBytes();

            try (FileOutputStream out = new FileOutputStream(fileTemp);
                 BufferedOutputStream bos = new BufferedOutputStream(out)) {
                bos.write(buffer, 0, buffer.length);
            } catch (Exception e) {
                System.out.println("Не удалось сохранить лог: " + e);
            }
        }
    }

    public static File createFolder(String mainFolder, String folderName) {
        File folder = new File(mainFolder, folderName);

        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);

        if (folder.exists()) {
            stringBuilder.append(strDate + " Каталог" + mainFolder + "\\" + folderName + " уже существует \n");
            return folder;
        } else if (folder.mkdir()) {
            stringBuilder.append(strDate + " Создан каталог " + mainFolder + "\\" + folderName + "\n");
            return folder;
        } else {
            String error = "Не удалось создать каталог " + mainFolder + "\\" + folderName + "\n";
            stringBuilder.append(error);
            System.out.println(error);
        }
        return null;
    }

    public static File createFolderInFolder(File mainFolder, String folderName) {
        File folder = new File(mainFolder, folderName);

        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);

        if (folder.exists()) {
            stringBuilder.append(strDate + " Каталог" + mainFolder + "\\" + folderName + " уже существует \n");
            return folder;
        } else if (folder.mkdir()) {
            stringBuilder.append(strDate + " Создан каталог " + mainFolder + "\\" + folderName + "\n");
            return folder;
        } else {
            String error = "Не удалось создать каталог " + mainFolder + "\\" + folderName + "\n";
            stringBuilder.append(error);
        }
        return null;
    }

    public static File createFile(File mainFolder, String fileName) {

        File newFile = new File(mainFolder, fileName);

        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);

        try {
            if (newFile.exists()) {
                stringBuilder.append(strDate + " Файл" + mainFolder + "\\" + fileName + " уже существует \n");
                return newFile;
            } else if (newFile.createNewFile()) {
                stringBuilder.append(strDate + " Создан файл " + mainFolder + "\\" + fileName + "\n");
                return newFile;
            }
        } catch (IOException ex) {
            stringBuilder.append(strDate + " Ошибка создания файла " + mainFolder + "\\" + fileName + " " + ex +"\n");
        }
        return null;
    }

}
