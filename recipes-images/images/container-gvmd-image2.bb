SUMMARY = "My Custom Image"
DESCRIPTION = "This is my customized image containing a simple hello-world"

LICENSE = "MIT"

include container-base.bb

#start of the resulting deployable tarball name
export IMAGE_BASENAME = "Greenbone-GVMD-Image2"
MACHINE_NAME ?= "${MACHINE}"
IMAGE_NAME = "${MACHINE_NAME}_${IMAGE_BASENAME}"

IMAGE_CLASSES += "extrausers"
EXTRA_USERS_PARAMS = "\
    useradd -p '' tester; \
    groupadd developers; \
    userdel nobody; \
    groupdel video; \
    groupmod -g 1020 developers; \
    usermod -s /bin/sh tester; \
"
OCI_IMAGE_AUTHOR = "Josef Fröhle"
OCI_IMAGE_AUTHOR_EMAIL = "github@josef-froehle.de"

OCI_IMAGE_ENTRYPOINT = "/bin/sh"
CONTAINER_SHELL = "busybox"


IMAGE_INSTALL:append = " \
    postgresql \
"