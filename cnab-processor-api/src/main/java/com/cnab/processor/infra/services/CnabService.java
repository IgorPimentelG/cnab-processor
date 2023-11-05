package com.cnab.processor.infra.services;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CnabService {

	private final Path fileStorageLocation;
	private final JobLauncher jobLauncher;
	private final Job job;

	public CnabService(
			@Value("${file.upload.dir}") String fileUploadDir,
			@Qualifier("jobLauncherAsync") JobLauncher jobLauncher,
			Job job) {
		this.fileStorageLocation = Paths.get(fileUploadDir);
		this.jobLauncher = jobLauncher;
		this.job = job;
	}

	public void uploadFile(MultipartFile file) throws Exception {
		var fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
		var targetLocation = fileStorageLocation.resolve(fileName);
		file.transferTo(targetLocation);

		var jobParameters = new JobParametersBuilder()
				.addJobParameter("cnab", file.getOriginalFilename(), String.class, true)
				.addJobParameter("cnabFile", "file:" + targetLocation.toString(), String.class, false)
				.toJobParameters();

		jobLauncher.run(job, jobParameters);
	}
}
