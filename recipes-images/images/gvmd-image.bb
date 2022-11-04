SUMMARY = "My Custom Image"
DESCRIPTION = "This is my customized image containing a simple hello-world"

LICENSE = "MIT"

inherit core-image

#start of the resulting deployable tarball name
export IMAGE_BASENAME = "Greenbone-GVMD-Image"
MACHINE_NAME ?= "${MACHINE}"
IMAGE_NAME = "${MACHINE_NAME}_${IMAGE_BASENAME}"

SYSTEMD_DEFAULT_TARGET = "multi-user.target"

IMAGE_LINGUAS = "en-us"

ROOTFS_PKGMANAGE_PKGS ?= '${@oe.utils.conditional("ONLINE_PACKAGE_MANAGEMENT", "none", "", "${ROOTFS_PKGMANAGE}", d)}'

IMAGE_INSTALL:append = " \
    packagegroup-boot \
    packagegroup-basic \
    udev-extra-rules \
    ${ROOTFS_PKGMANAGE_PKGS} \
"

IMAGE_DEV_MANAGER   = "udev"
IMAGE_INIT_MANAGER  = "systemd"
IMAGE_INITSCRIPTS   = " "
IMAGE_LOGIN_MANAGER = "busybox shadow"

IMAGE_CLASSES += "extrausers"
EXTRA_USERS_PARAMS = "\
    useradd -p '' tester; \
    groupadd developers; \
    userdel nobody; \
    groupdel video; \
    groupmod -g 1020 developers; \
    usermod -s /bin/sh tester; \
"