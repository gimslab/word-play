./gradlew clean build
scp build/libs/word-play-0.0.1-SNAPSHOT.jar gimspi:projects/word-play/
ssh gimspi 'cd projects/word-play/ && sh restart.sh'

