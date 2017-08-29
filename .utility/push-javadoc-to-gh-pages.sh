#!/bin/bash
if [ "$TRAVIS_REPO_SLUG" == "openmessaging/openmessaging-java" ] && [ "$TRAVIS_JDK_VERSION" == "oraclejdk8" ] && [ "$TRAVIS_PULL_REQUEST" == "false" ] && [ "$TRAVIS_BRANCH" == "master" ]; then

  echo -e "Publishing javadoc...\n"
  mvn javadoc:aggregate -pl :parent,:openmessaging-api
  rm -rf $HOME/javadoc-latest
  cp -R target/site/apidocs/ $HOME/javadoc-latest

  cd $HOME
  git config --global user.email "travis@travis-ci.org"
  git config --global user.name "travis-ci"
  git clone --quiet --branch=gh-pages https://${GH_TOKEN}@github.com/openmessaging/openmessaging-java gh-pages > /dev/null

  cd gh-pages
  git rm -rf .
  cp -Rf $HOME/javadoc-latest/. .
  git add -f .
  git commit -m "Latest javadoc on successful travis build $TRAVIS_BUILD_NUMBER auto-pushed to gh-pages"
  git push -fq origin gh-pages > /dev/null

  echo -e "Published Javadoc to gh-pages.\n"
  
fi
