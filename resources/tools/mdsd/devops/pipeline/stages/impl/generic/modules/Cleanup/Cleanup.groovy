MPLModule("Cleanup")
sh "ls -A1 | xargs -d '\n' rm -rf"