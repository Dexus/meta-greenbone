SUMMARY = "Package lighttpd app container image"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

do_compile[noexec] = "1"

do_install[mcdepends] = "multiconfig::container:app-container-image-lighttpd:do_image_complete"

do_install () {
    install -d ${D}/var/lib/machines
    #install ${TOPDIR}/tmp-container/${DEPLOY_DIR_IMAGE}/app-container-image-lighttpd.ext4
    install ${TOPDIR}/tmp-container/deploy/images/qemux86-64-container/app-container-image-lighttpd-qemux86-64-container.ext4 \
        ${D}/var/lib/machines
}

RDEPENDS_${PN} += "systemd-container"