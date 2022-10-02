FROM ubuntu

EXPOSE 4200

WORKDIR usr/bin/ci

ADD ./* .

RUN ls

RUN apt-get update

RUN apk add nodejs npm

RUN apk add nodejs-current

RUN npm install -g npm@latest

RUN npm npm install -g @angular/cli

CMD ["ng","serve"]
