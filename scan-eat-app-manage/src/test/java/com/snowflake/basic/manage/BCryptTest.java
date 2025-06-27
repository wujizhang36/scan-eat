package com.snowflake.basic.manage;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.alibaba.fastjson2.JSON;
import com.snowflake.basic.modules.common.pojo.Kennel;
import com.snowflake.basic.modules.common.pojo.Pet;
import com.snowflake.basic.modules.common.pojo.Registration;
import com.snowflake.basic.modules.common.utils.*;
import com.snowflake.basic.modules.manage.service.MFileService;
import com.snowflake.basic.modules.manage.service.MKennelService;
import com.snowflake.basic.modules.manage.service.MPetService;
import com.snowflake.basic.modules.manage.service.MRegistrationService;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.crypto.Cipher;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;


@SpringBootTest(classes = ManageApplication.class)
@RunWith(SpringRunner.class)
public class BCryptTest {

    @Test
    public void authTest2() throws Exception {
        String str = RSAUtil.encryptRSA("my_secure_password");
//        String str = RSAUtil.encryptRSA("admin");
        System.out.println(str);
        String str2 = RSAUtil.decryptRSA(str);

        System.out.println(str2);
    }

    /**
     * BCrypt加密 密码加密
     */
    @Test
    public void bCrypt() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // 生成密码哈希
        String rawPassword = "my_secure_password";
        String hashedPassword = passwordEncoder.encode(rawPassword);
        System.out.println("Hashed Password: " + hashedPassword);

        // 验证密码（登录时）
        boolean matches = passwordEncoder.matches(rawPassword, hashedPassword);
        System.out.println("Password Matches: " + matches);
    }

    @Test
    public void RSAKeyGenerator() throws NoSuchAlgorithmException {
        /*生成 RSA 公私钥*/
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair keyPair = keyGen.generateKeyPair();
        System.out.println("Public Key:\n" + Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded()));
        System.out.println("Private Key:\n" + Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded()));
    }

    /**
     * 使用公私钥加密密码，用于前后端传输
     */
    @Test
    public void authTest() throws Exception {
        // 1️⃣ 前端：生成 AES 密钥
        String aesKey = AESUtil.generateAESKey();
        System.out.println("AES 密钥: " + aesKey);

        // 2️⃣ 前端：用 RSA 公钥加密 AES 密钥
        String encryptedAESKey = RSAUtil.encryptRSA(aesKey);
        System.out.println("加密后的 AES 密钥: " + encryptedAESKey);

        // 3️⃣ 前端：用 AES 加密密码
        String password = "mySuperSecurePassword";
        String encryptedPassword = AESUtil.encryptAES(password, aesKey);
        System.out.println("加密后的密码: " + encryptedPassword);

        // 🔥 发送 encryptedAESKey 和 encryptedPassword 到后端 🔥

        // 4️⃣ 后端：用 RSA 私钥解密 AES 密钥
        String decryptedAESKey = RSAUtil.decryptRSA(encryptedAESKey);
        System.out.println("解密后的 AES 密钥: " + decryptedAESKey);

        // 5️⃣ 后端：用 AES 解密密码
        String decryptedPassword = AESUtil.decryptAES(encryptedPassword, decryptedAESKey);
        System.out.println("解密后的密码: " + decryptedPassword);
    }


    // RSA 加密
    public static String encryptRSA(String data) throws Exception {
        // RSA 公钥（前端用）
        final String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzTdAbjenLOAV2XBsDqXCRlngWwdq5BbAhKx0BNuzYxYA8dNbKCXO7k4V6nKH1J8wVjTjmj3NPKo+hfS2B+hfK3N+AXN+i4Z0YWE5hqCfO6Xb/JSmVU2n7UDv0qLeQMc8jH5Vk9h7TbwzHS8+2cx/jnNtoxSyF1TQ5l0TqKdBCjb9vkqmmsU0I4NwfxZz0e5wyemlog0mioGDHcDJ0qu+C7pdtQszY2rVxVzI2ux+y7VrzxcgNsOax4qTxfoaeWq/00eVBlcZNzScFkru8M+mqbY1K+NRGUeDW3x4rofr1NEXiekiNgIagS7XNgpncxZczWIEDX5PkZl+GXuHhFj9NQIDAQAB";

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(
                new X509EncodedKeySpec(Base64.getDecoder().decode(PUBLIC_KEY))
        );

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());

        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // RSA 解密
    public static String decryptRSA(String encryptedData) throws Exception {
        // RSA 私钥（后端用）
        final String PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDNN0BuN6cs4BXZcGwOpcJGWeBbB2rkFsCErHQE27NjFgDx01soJc7uThXqcofUnzBWNOOaPc08qj6F9LYH6F8rc34Bc36LhnRhYTmGoJ87pdv8lKZVTaftQO/Sot5AxzyMflWT2HtNvDMdLz7ZzH+Oc22jFLIXVNDmXROop0EKNv2+SqaaxTQjg3B/FnPR7nDJ6aWiDSaKgYMdwMnSq74Lul21CzNjatXFXMja7H7LtWvPFyA2w5rHipPF+hp5ar/TR5UGVxk3NJwWSu7wz6aptjUr41EZR4NbfHiuh+vU0ReJ6SI2AhqBLtc2CmdzFlzNYgQNfk+RmX4Ze4eEWP01AgMBAAECggEAA3tkhHo9Q+2pcT8PFlCJs1fjDqhZj/Uy3w1uCocPzCabbX8AlPXPRQveI1Q+KcVptIzBL+TMjrL0zKESVaADvWEASlYDV6q4Xs6fg0FGcmSmDusWx2vB5aysZQ6OZtr6lAewvAl4TiFbXKtojZmCsJAU1CeNvgeaY85AfyQXXmz4pZc6XnQjDKoXtpprvFx6xAF14qYx8buHFcG7YHi/UVuA85KqqU63xvzs0C4FMkrG2ZPneuFenaILesS0yUaE2Zc9sew6Fcuww4+/HyN0c83OTNrPTP8u7OW/Pvy2bUZMtHpWGguGzz0xUzYlKY8FUYtkOrtvTKprYv8d8rpJIQKBgQDWctciIyBWA08KFq8JOjgI/c2s8o44rVZ3uYfStKrNQl9dbqhZAE/yti2sIXIXnQdOlrZpnMrzjwiL6FRCvbsyQ31F/U3+p8MeZvbh4uuxEDXLf+rao5s2GXTO2xG45ap0PlkQR0243kHMMsVX4FyyNf+E3ITKA71OGqgSegAQawKBgQD0+nGvXFyxvWjNYmMxx4w/Kuf3vUpNpn+TYjFWYTK3NV4nKe1jmgoPyfUkwgjR7jnSjHaSC/Z3kMI8KvUjtWZQwmq0D/XDiJaoN3+ifaSDlUxcKstN/HfUmzLpMlH1syCoLlDbg4J1QjwbfsmUPuJxXiC1CIi1ani44MgweMwQ3wKBgQC9bfYxlnvC3utbywvVB9NlxeR/6EbPM/bUjVO4QZU6+W1KGCynaco1aM3RZj3lM/W3axQ5GKKCIQ/4nkKILhcE4G7xez8BOun1mhhtEskFwH70gstPVQQO9s+ixgrSnDVEt6pu4b17FwFiQlrmNC9N0LskfrLhzAnVy2A56gDSfwKBgARsDOdMdIPk1mJ2tzGr13VAcgV/zjqOmdjwOdHEWWUDOprcX+OfYTUdwSYTtpMLiNGrJWzHeIB6BXeOkvQ9DwbAK6rqatZHveh1cAFf4Tta05EKWLckqokU8BtThDU6MYm3pFACFLXHJ0ybwknBxNalC1QZPlaAAIPd0t9FHvHjAoGAKfO04G6qCJ00hkxIZwiKM48xgenNjhe/aQC+RsRGbwDDTGeBxd+nxCvkNs64MAU3vq1tOqQAP/XL4Rzig0RKOznuVZXwf0x4OXOuaXfBrwis0FX6X0Kd4HDG157rzNUll4H0x1EGu1ZJ9wbJcHawThyALeBHIkm1BljJTGP9c4o=";

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(
                new PKCS8EncodedKeySpec(Base64.getDecoder().decode(PRIVATE_KEY))
        );
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(decryptedBytes);
    }


//    @Resource
//    MExcelImporterService mExcelImporterService;

//    @Test
//    public void excelImporter() throws IOException {
//        try {
//            String filePath = "C:\\Users\\Craig\\Desktop\\犬舍\\csv\\pets.csv";  // 替换为你的文件路径
//            mExcelImporterService.excelImporterCsv(filePath);
//        } catch (IOException | CsvException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

//    @Test
//    public void petBindingKennel() throws IOException {
//        mExcelImporterService.petBindingKennel();
//    }

    @Resource
    MinioUtil minioUtil;

    @Test
    public void minioUploadFileTest() throws IOException {
        final String objectName = "images/test121234";
        // 文件路径
        String filePath = "https://bully-registry.oss-cn-hongkong.aliyuncs.com/u=4057990941,1108075560&fm=253&fmt=auto&app=138&f=JPEG.webp";

        URL url = new URL(filePath);
        InputStream stream = url.openStream();
        long size = url.openConnection().getContentLengthLong();  // 获取文件大小

        // 上传文件
        String result = minioUtil.uploadFile(objectName, stream, size, "image/jpeg"); //"image/jpeg"
        System.out.println(ImageDownloaderUtils.getFileExtension(filePath));
        System.out.println(result + ImageDownloaderUtils.getFileExtension(filePath));
        stream.close();
    }

    @Test
    public void minioDelFileTest() throws IOException {
        final String objectName = "image/importer/894648832.jpg";
        minioUtil.deleteFile(objectName);
    }

    @Resource
    MPetService mPetService;

    @Resource
    MFileService fileService;

    @Resource
    MRegistrationService registrationService;

//    @Test
//    public void minioFileImport() throws IOException {
//        // 图片保存到数据库, 上传到minio桶
//        List<java.io.File> files = FileUtils.listFiles("C:\\Users\\Craig\\Desktop\\犬舍\\imageMigration");
//
//        List<Pet> petList = mPetService.list();
//        Map<String, Pet> petMap = new HashMap<>();
//        List<com.snowflake.basic.modules.common.pojo.File> petFiles = new ArrayList<>();
//        for (Pet pet : petList) {
//            petMap.put(pet.getIdStr(), pet);
//        }
//        int i = 1;
//        for (java.io.File file : files) {
//            System.out.println(i + "/" + files.size());
//            String path = "image/importer/";
//            String key = ImageDownloaderUtils.getFileNameWithoutExtension(file.getName());
//            Pet pet = petMap.get(key);
//            if (ObjectUtils.isEmpty(pet)) {
//                continue;
//            }
//            String tagPath = path.concat(file.getName());
//            com.snowflake.basic.modules.common.pojo.File petFile = new com.snowflake.basic.modules.common.pojo.File();
//            petFile.setPetId(pet.getId());
//            petFile.setKey(key);
//            petFile.setName(tagPath);
//            petFile.setImgOfId(tagPath);
//            petFile.setAvatarOfId(tagPath);
//            petFile.setSize((int) file.length());
//            petFile.setUrl(MinioUtilShowPath.getShowImage().concat(tagPath));
//            petFiles.add(petFile);
//
//            // 上传文件🎯
//            imgUpload(file, tagPath);
//            i++;
//        }
//
////        fileService.saveOrUpdateBatch(petFiles);
//    }
//
//    void imgUpload(java.io.File file, String uploadPathAndName) {
//        try (InputStream inputStream = new FileInputStream(file)) {
//            // 上传文件
//            String result = minioUtil.uploadFile(uploadPathAndName, inputStream, file.length(), "image/jpeg"); //"image/jpeg"
//            System.out.println("上传成功：" + result);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    @Test
    public void generateDataReadableId() { // 随机生成芯片ID
        List<Pet> petList = mPetService.list();
        List<Registration> notExistRegistrationList = registrationService.list();
        for (Registration pet : notExistRegistrationList) {
//            LocalDateTime time = randomData();
//            Registration registration = new Registration();
//            registration.setReadableId(IdGeneratorUtil.generateID(9));
//            registration.setReviewedById(1L);
//            registration.setPetId(pet.getId());
//            registration.setStatus("APPROVED");
//            registration.setUpdatedAt(time);
//            registration.setReviewedAt(time);
//            registration.setRegisterEnd(time);
//            registration.setRegisteredAt(time);
//            notExistRegistrationList.add(registration);
            pet.setReadableId(NumericIdGeneratorUtil.generateId(9));
        }
        registrationService.updateBatchById(notExistRegistrationList);
        System.out.println(notExistRegistrationList.size());
    }

    @Resource
    MKennelService kennelService;

    @Test
    public void petBindingKennel() { // 每个犬舍绑定pet
        List<Pet> petList = mPetService.list();
        // 获取所有未绑定 kennel 的 pet
        List<Pet> notKennelList = petList.stream()
                .filter(pet -> ObjectUtils.isEmpty(pet.getKennelId()))
                .collect(Collectors.toList());

        System.out.println("未绑定kennel的pet数量: " + notKennelList.size());

        List<Kennel> kennelList = kennelService.list();
        List<Long> kennelIdList = kennelList.stream()
                .map(Kennel::getId)
                .collect(Collectors.toList());

        System.out.println("Kennel数量: " + kennelIdList.size());

        if (notKennelList.size() < kennelIdList.size()) {
            throw new IllegalStateException("Pet数量不足，无法保证每个Kennel至少一个Pet");
        }

        // 打乱顺序，避免偏差
        Collections.shuffle(notKennelList);
        Collections.shuffle(kennelIdList);

        int totalPets = notKennelList.size();
        int totalKennels = kennelIdList.size();
        int average = totalPets / totalKennels;
        int remainder = totalPets % totalKennels;

        int index = 0;
        List<Pet> toUpdate = new ArrayList<>();

        for (int i = 0; i < totalKennels; i++) {
            Long kennelId = kennelIdList.get(i);
            int assignCount = average + (i < remainder ? 1 : 0); // 均分，前 remainder 个多分一个
            for (int j = 0; j < assignCount; j++) {
                Pet pet = notKennelList.get(index++);
                pet.setKennelId(kennelId);
                toUpdate.add(pet);
            }
        }

        // 批量更新数据库
        mPetService.updateBatchById(toUpdate);

        System.out.println("绑定完成，每个Kennel平均绑定Pet数量: " + average + " 或 " + (average + 1));
    }



    public LocalDateTime randomData() {
        Random random = new Random();

        // 设置起始日期：2020年1月1日
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.set(2020, Calendar.JANUARY, 1);

        // 获取当前日期
        Calendar endCalendar = Calendar.getInstance();

        // 计算日期差
        long startMillis = startCalendar.getTimeInMillis();
        long endMillis = endCalendar.getTimeInMillis();
        long diffMillis = endMillis - startMillis;

        // 随机生成一个日期
        long randomMillis = startMillis + (long) (random.nextDouble() * diffMillis);
        Date randomDate = new Date(randomMillis);

        // 输出随机日期
        System.out.println("随机日期: " + randomDate);

        // 将 Date 转换为 Instant
        Instant instant = randomDate.toInstant();

        // 将 Instant 转换为 LocalDateTime
        LocalDateTime localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
        return localDateTime;
    }

    @Test
    public void sa_token_encryption() throws Exception {
        System.out.println(SaSecureUtil.rsaGenerateKeyPair());
    }

    @Test
    public void fastjson_test() throws Exception {
        String maliciousPayload = """
                {
                  "@type": "com.sun.rowset.JdbcRowSetImpl",
                  "dataSourceName": "127.0.0.1:8080/callback",
                  "autoCommit": true
                }
                """;
        try {
            Object obj = JSON.parseObject(maliciousPayload);
            System.out.println("解析成功（存在潜在风险）：" + obj.getClass());
        } catch (Exception e) {
            System.out.println("拦截成功，fastjson2 配置安全！");
            e.printStackTrace();
        }


    }


}
