package com.example.demo;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.TextProgressMonitor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
@RequestMapping("/repo")
public class GitRepoController {

    @GetMapping("/clone")
    public void cloneGitHubRepo(@RequestParam String url) throws GitAPIException {
        // prepare a new folder for the cloned repository
        String desktopPath = System.getProperty("user.home") + File.separator + "Desktop";   // temporary
        String repoName = parseRepoName(url);
        File directory = new File(desktopPath, repoName);

        // then clone
        System.out.println("Cloning from " + url + " to " + directory);
        try (Git result = Git.cloneRepository()
                .setURI(url)
                .setDirectory(directory)
                .setProgressMonitor(new TextProgressMonitor())
                .call()) {
            // Note: the call() returns an opened repository already which needs to be closed to avoid file handle leaks!
            System.out.println("Having repository: " + result.getRepository().getDirectory());
        }
    }

    private String parseRepoName(final String url) {
        String[] split = url.replace(".git", "").split("/");
        return split[split.length - 1];
    }
}
