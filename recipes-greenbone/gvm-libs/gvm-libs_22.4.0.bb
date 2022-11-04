SUMMARY = "gvm-libs recipe"
DESCRIPTION = "Recipe for gvm-libs from greenbone"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"

PR="0"
inherit logging

SRCREV = "6d01858f7128b3ac5fd8ba8d2b728caa77e7b790"
SRC_URI ="git://github.com/greenbone/gvm-libs.git;branch=main;protocol=https"

S = "${WORKDIR}/git"

DEPENDS = "gnutls gpgme util-linux-libuuid libpcap libssh hiredis libxml2 libnet zlib libgcrypt glib-2.0 openldap paho-mqtt-c libradcli4"
DEPENDS:append = " clang"
RDEPENDS:${PN} = "gnutls gpgme util-linux-libuuid libpcap libssh hiredis libxml2 libnet zlib libgcrypt glib-2.0 openldap paho-mqtt-c libradcli4"

inherit cmake pkgconfig

PACKAGECONFIG ??=""

EXTRA_OECMAKE=""
OECMAKE_GENERATOR="Unix Makefiles"
do_configure:prepend (){
    bbwarn "${S}"
}

do_install(){
    make install
}

python do_display_banner() {
    bb.plain("***********************************************");
    bb.plain("*                                             *");
    bb.plain("*               gvm-libs recipe               *");
    bb.plain("*                                             *");
    bb.plain("***********************************************");
}

addtask display_banner before do_build
