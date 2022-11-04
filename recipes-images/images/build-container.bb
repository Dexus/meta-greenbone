SUMMARY = "A minimal bootstrap container image"

IMAGE_FSTYPES = "container"

inherit core-image

IMAGE_INSTALL = " \
    packagegroup-core-boot \
    packagegroup-self-hosted-sdk \
    packagegroup-self-hosted-extended \
    ${CORE_IMAGE_EXTRA_INSTALL} \
"

IMAGE_LINGUAS = "en-us"
MACHINE_ESSENTIAL_EXTRA_RDEPENDS = ""

IMAGE_CONTAINER_NO_DUMMY = "1"

# Workaround /var/volatile for now
ROOTFS_POSTPROCESS_COMMAND += "rootfs_fixup_var_volatile ; "

rootfs_fixup_var_volatile () {
    install -m 1777 -d ${IMAGE_ROOTFS}/${localstatedir}/volatile/tmp
    install -m 755 -d ${IMAGE_ROOTFS}/${localstatedir}/volatile/log
}