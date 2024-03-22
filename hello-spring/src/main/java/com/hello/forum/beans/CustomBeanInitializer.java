package com.hello.forum.beans;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import jakarta.annotation.PostConstruct;

/**
 * Spring 이 기본적으로 생성하는 Bean 이외의 
 * 개발자가 직접 만든 클래스의 Bean 을 Bean Container 에 적재 시ㅣ는 역할.
 * 
 * @SpringBootConfiguration 의 역할
 * 	Spring Boot Application 이 실행 될 때 (서버를 Run 시킬 때)
 *  Spring Boot 가 어노테이션이 적용된 클래스를 찾아 실행 시키고
 *  필요한 Bean들을 Bean Container 에 적재한다.
 */
@SpringBootConfiguration
public class CustomBeanInitializer {
	
	/**
	 * application.yml 파일에 작성된
	 * 사용자 환경설정 정보들을 읽어와서
	 * 맴버변수로 할당해준다.
	 */
	
	@Value("${app.multipart.base-dir:c:/uploadFiles}")
	private String baseDir;
	
	@Value("${app.multipart.obfuscation.enable:false}")
	private boolean enableObfuscation;
	
	@Value("${app.multipart.obfuscation.hide-ext.enable:false}")
	private boolean enableObfuscationHideExt;
	
	@Value("${app.multipart.available-file-list}")
	private List<String> availableFileList;
	

	public CustomBeanInitializer() {
		System.out.println("Customeaadsfadsf 실행됨!!!!!!!!!");
		// 생성자가 실행된 이후의 값들을 봐야 값이 들어왔는지 제대로 확인할 수 있다.
		// 생성자가 실행되는 시점에는 yml 의 값이 할당되지 않는다.
		// 생성자가 실행되고 난 이후의 시점에서 yml 의 값이 할당된다.
		System.out.println("baseDir: " + baseDir);
		System.out.println("enableobfuscation: " + enableObfuscation);
		System.out.println("enableObfuscationHideExt: " + enableObfuscationHideExt);
		System.out.println("> availableFileList: " + availableFileList);
	}
	
	/**
	 * 스프링이 클래스를 객체화 시키고
	 * 필요한 값들이나 객체를 모두 할당한 이후에
	 * @PostConstruct 가 적용된 메서드를 실행시킨다.
	 */
	@PostConstruct
	public void postConstructor() {
		System.out.println("생성자가 실행된 이후의 시점.");
		System.out.println("baseDir: " + baseDir);
		System.out.println("enableobfuscation: " + enableObfuscation);
		System.out.println("enableObfuscationHideExt: " + enableObfuscationHideExt);
	
	}
	
	// @Bean 이 적용되는 메서드를 쓸 경우에는 public 을 쓰지 않는다. (Bean 컨테이너에 들어가 전역에서 접근이 가능해짐)
	/**
	 * @Bean 어노테이션이 적용된 메서드가 실행되면
	 * 이 메서드가 반환하는 객체를 Bean Container 에 적재를 한다.
	 * 
	 * 메서드의 이름이 Bean 객체의 이름이 된다. (어디서든 사용 가능)
	 * @return
	 */
	@Bean
	FileHandler fileHandler() {
		
		FileHandler fileHandler = new FileHandler();
		fileHandler.setBaseDir(this.baseDir);
		fileHandler.setEnableObfuscation(this.enableObfuscation);
		fileHandler.setEnableObfuscationHideExt(this.enableObfuscationHideExt);
		fileHandler.setAvailableFileList(this.availableFileList);
		return fileHandler;
	}
}
