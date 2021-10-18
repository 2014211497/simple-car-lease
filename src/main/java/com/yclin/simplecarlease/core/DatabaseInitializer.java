package com.yclin.simplecarlease.core;

import com.yclin.simplecarlease.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * @author LinYuchang
 */
@Slf4j
@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final ApplicationProperties properties;

    @Resource
    private DataSource dataSource;

    public DatabaseInitializer(ApplicationProperties properties) {
        this.properties = properties;
    }

    @Override
    public void run(String... args) throws Exception {
        try (Connection connection = dataSource.getConnection()) {

            // 获取当前资源目录下的所有文件对象
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            List<org.springframework.core.io.Resource> resources =
                    Arrays.asList(resolver.getResources(properties.getSchemaLocation()));
            resources.sort(Comparator.comparing(org.springframework.core.io.Resource::getFilename));

            for (org.springframework.core.io.Resource resource : resources) {
                // get the name of table by splitting filename
                String tableName = Objects.requireNonNull(resource.getFilename()).replace(".sql", "");

                log.info("creating table {} using sql file: {}", tableName, resource.getFilename());
                String content = new String(FileUtil.readAsBytes(resource.getInputStream()), StandardCharsets.UTF_8);
                // execute the commands one by one
                for (String sql : content.split(";")) {
                    if (sql.trim().isEmpty()) {
                        continue;
                    }
                    connection.createStatement().execute(sql);
                }
                log.info("created table {}", tableName);
            }
        }
    }
}
