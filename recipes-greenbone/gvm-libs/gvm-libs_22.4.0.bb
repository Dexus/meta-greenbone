SUMMARY = "gvm-libs recipe"
DESCRIPTION = "Recipe for gvm-libs from greenbone"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"

PR="r0"

inherit logging

SRCREV = "6d01858f7128b3ac5fd8ba8d2b728caa77e7b790"
SRC_URI = "git://github.com/greenbone/gvm-libs.git;branch=main;protocol=https \
           file://0001-Fix-CMake-lib-checks-to-find-all-libs.patch \
           "

S = "${WORKDIR}/git"

DEPENDS = "gnutls gpgme util-linux-libuuid libpcap libssh hiredis libxml2 libnet zlib libgcrypt glib-2.0 openldap paho-mqtt-c libradcli4"
DEPENDS:append = " clang"
RDEPENDS:${PN} = "gnutls gpgme util-linux-libuuid libpcap libssh hiredis libxml2 libnet zlib libgcrypt glib-2.0 openldap paho-mqtt-c libradcli4"

inherit cmake pkgconfig

PACKAGECONFIG ??=""

EXTRA_OECMAKE = " -DCMAKE_BUILD_TYPE=Release -DEXEC_PREFIX=${exec_prefix} -DLIBDIR=${baselib} "
OECMAKE_GENERATOR="Unix Makefiles"

FILES:${PN}:append = " /run"

INSANE_SKIP:${PN} += "empty-dirs"
