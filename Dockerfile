FROM bellsoft/liberica-openjdk-alpine:21

#Create workspace
WORKDIR /home/selenium-docker

#Install curl and jq
RUN apk add curl jq 

ADD target/docker-resources ./
ADD runner.sh runner.sh
 
#Execute the runner file
ENTRYPOINT sh runner.sh 
