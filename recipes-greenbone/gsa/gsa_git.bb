# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "Greenbone Security Assistant"
LICENSE = "AGPL-3.0"
LICENSE:${PN} = "AGPL-3.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5583b9b75bd9ffa2b42bf8f3d047eb7a"

SRC_URI = " \
    git://github.com/greenbone/gsa.git;protocol=https;branch=stable \
    npmsw://${THISDIR}/${BPN}/npm-shrinkwrap.json;dev=1 \
    "

# Modify these as desired
PV = "22.4.0+git${SRCPV}"
SRCREV = "6bc67416b2d34f4acd326b6632bce47253bb29c4"

S = "${WORKDIR}/git"

NPM_ARCH = "${BUILD_ARCH}"
PACKAGE_ARCH = "all"

inherit npm
RDEPENDS:${PN}:remove:class-target = " nodejs"
FILES:${PN} += "${datadir}/gvm/gsad/web/*"
INSANE_SKIP:${PN} += "obsolete-license"

DEPENDS:append = " iputils "


#NPM_INSTALL_DEV = "1"


do_my_compile(){
    cd ${S}
    rm -rf build || true
    export HOME=${WORKDIR};
    OLDPATH=${PATH}
    export PATH="${PATH}:${S}/node_modules/.bin/"
    export npm_config_cache="${WORKDIR}/npm-cache" ;
    export npm_config_offline="false" ;
    export npm_config_proxy="false" ;
    export npm_config_https_proxy="" ;
    npm install yarn
    yarn
    yarn build
    export PATH=${OLDPATH}
}

addtask do_my_compile after do_compile before do_install

do_install () { 
    install -d ${D}${datadir}/gvm/gsad/web/
    (cd ${S} && find build -type f -exec install -Dm 755 "{}" "${D}${datadir}/gvm/gsad/web/{}" \;)
}
do_my_compile[network] = "1"
do_configure[noexec] = "1"
do_compile[noexec] = "1"
