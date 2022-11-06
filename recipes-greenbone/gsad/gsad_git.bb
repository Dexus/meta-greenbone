# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

LICENSE = "AGPL-3.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5583b9b75bd9ffa2b42bf8f3d047eb7a"

SRC_URI = "git://github.com/greenbone/gsad.git;protocol=https;branch=stable \
           file://0001-fix-build-and-use-pkg-config-for-libgcrypt.patch \
           "

# Modify these as desired
PV = "22.4.0+git${SRCPV}"
SRCREV = "cda13d1083a2c72e2a5bddb386d7a3406eda31a7"
PROVIDES = "gsad"

S = "${WORKDIR}/git"

DEPENDS = "gsa libmicrohttpd gnutls gvm-libs libgcrypt libxml2 glib-2.0"
RDEPENDS:${PN} = "gsa libmicrohttpd gnutls gvm-libs libgcrypt libxml2 glib-2.0"

FILES:${PN} += " ${baselib}"

inherit cmake pkgconfig
OECMAKE_GENERATOR="Unix Makefiles"
# Specify any options you want to pass to cmake using EXTRA_OECMAKE:
EXTRA_OECMAKE = " -DCMAKE_BUILD_TYPE=Release -DEXEC_PREFIX=${exec_prefix} -DLIBDIR=${baselib} "

INSANE_SKIP:${PN} += "obsolete-license"