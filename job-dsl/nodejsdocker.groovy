job('NodeJS Docker example') {
    scm {
        git('git://github.com/abhinotes/docker-demo.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('Abhishek Kumar')
            node / gitConfigEmail('gabhishekkumar@outlook.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('Nodejs') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('coolmagnet/jenkin-node-js-demo')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
