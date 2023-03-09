package com.petfam.petfam.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;


class S3ControllerTest {

  @Mock
  private AmazonS3 amazonS3;

  @InjectMocks
  private S3Controller controller;

  @Value("${cloud.aws.s3.bucket}")
  private String bucketName;

  @BeforeEach
  void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void uploadFileTest() throws IOException {
    // given
    MockMultipartFile file = new MockMultipartFile("file", "file.txt", "text/plain",
        "This is a test file".getBytes());

    String bucketName = "test-bucket";
    String filename = "testfile.txt";
    when(amazonS3.generatePresignedUrl(any(GeneratePresignedUrlRequest.class)))
        .thenReturn(new URL("https://" + bucketName + ".s3.amazonaws.com/" + filename));

    // when
    ResponseEntity<String> response = controller.uploadFile(file);

    // then
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertTrue(response.getBody().contains(filename));
  }

  @Test
  public void deleteFileTest() {
    // given
    String fileName = "test.jpg";
    doNothing().when(amazonS3).deleteObject(eq(bucketName), eq(fileName));

    // when
    ResponseEntity<String> response = controller.deleteFile(fileName);

    // then
    verify(amazonS3).deleteObject(eq(bucketName), eq(fileName));
    assertEquals("File deleted successfully", response.getBody());
    assertEquals(200, response.getStatusCodeValue());
  }

  @Test
  void getUrlFile() throws MalformedURLException{
    // given
    String fileName = "test.jpg";
    String expectedUrl = "https://test-bucket.s3.amazonaws.com/test.jpg";
    Date expiration = new Date(System.currentTimeMillis() + 3600000);
    GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, fileName)
        .withMethod(HttpMethod.GET)
        .withExpiration(expiration);
    URL url = new URL(expectedUrl);
    when(amazonS3.generatePresignedUrl(any(GeneratePresignedUrlRequest.class))).thenReturn(url);

    // when
    ResponseEntity<String> response = controller.getUrlFile(fileName);

    // then
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(expectedUrl, response.getBody());
  }
}