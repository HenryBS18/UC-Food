FROM node:20-alpine

WORKDIR /app

COPY package*.json .
COPY public ./public
COPY views ./views
COPY app.js ./app.js

RUN npm install

EXPOSE 3000

CMD [ "node", "app.js" ]