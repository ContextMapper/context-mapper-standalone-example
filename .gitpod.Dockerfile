FROM gitpod/workspace-full

# Install custom tools, runtimes, etc.
# For example "bastet", a command-line tetris clone:
# RUN brew install bastet
#
# More information: https://www.gitpod.io/docs/config-docker/

# Install Graphviz
RUN sudo apt-get update \
    && sudo apt-get -y install graphviz

# Install JHipster
RUN npm install -g generator-jhipster
