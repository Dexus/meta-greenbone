SUMMARY = "A simple RADIUS client library"
DESCRIPTION = "The radcli library is a library for writing RADIUS Clients. \
The library's approach is to allow writing RADIUS-aware application in less \
than 50 lines of C code. It was based originally on freeradius-client and \
radiusclient-ng and is source compatible with them."
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=98f20548b0e2bc66e5583e541fb647bd"

PR="2"

inherit autotools-brokensep pkgconfig

DEPENDS += " nettle libtool gnutls"


SRCREV = "4013b4fcce481a19e4effd5c5fb520a290dc00b8"
SRC_URI ="git://github.com/radcli/radcli.git;branch=master;protocol=https"
SRC_URI[sha256sum] = "1d7ffaf9f78c18bf5c6348b2491be1ee17390172636fa87bd7719d669cb46faf"
S = "${WORKDIR}/git"

PACKAGECONFIG_CONFARGS = "--with-tls"

FILES:${PN} = "${bindir}/* ${sbindir}/* ${libexecdir}/* ${libdir}/lib*${SOLIBS} \
            ${sysconfdir} ${sharedstatedir} ${localstatedir} ${datadir} \
            ${base_bindir}/* ${base_sbindir}/* \
            ${base_libdir}/*${SOLIBS} \
            ${datadir}/${PN} ${libdir}/${PN}/* \
            ${datadir}/pixmaps ${datadir}/applications \
            ${datadir}/idl ${datadir}/omf ${datadir}/sounds \
            ${libdir}/bonobo/servers"
RDEPENDS:${PN} = "nettle libtool gnutls"

FILES:${PN}-dev = "${includedir} ${libdir}/lib*${SOLIBSDEV} ${libdir}/*.la \
                ${libdir}/*.a ${libdir}/*.o ${libdir}/pkgconfig \
                ${base_libdir}/*.a ${base_libdir}/*.o ${datadir}/aclocal"
ALLOW_EMPTY:${PN}-dev = "1"



FILES:${PN}-doc = "${docdir} ${mandir} ${infodir} ${datadir}/gtk-doc \
            ${datadir}/gnome/help"

FILES:${PN}-dbg = "${bindir}/.debug ${sbindir}/.debug ${libexecdir}/.debug ${libdir}/.debug \
            ${base_bindir}/.debug ${base_sbindir}/.debug ${base_libdir}/.debug ${libdir}/${PN}/.debug \
            ${libdir}/matchbox-panel/.debug"
ALLOW_EMPTY:${PN}-dbg = "1"

do_configure:prepend () { 
    # Remove any existing libtool m4 since old 
    # stale versions would break any upgrade 
     ( cd ${S}; touch config.rpath; autoreconf -vfi )
    
}


do_install:append () {
    install -d ${D}${sysconfdir}
    cp ${D}${datadir}/radcli/* ${D}/${sysconfdir}/radcli/
    #(cd ${libdir} ; ln -s -r libradcli.so.4 libradcli.so ; ln -s -r libradcli.so.4.3.0 libradcli.so )
}

python do_display_banner() {
    bb.plain("***********************************************");
    bb.plain("*                                             *");
    bb.plain("*                radcli libary                *");
    bb.plain("*                                             *");
    bb.plain("***********************************************");
}

addtask display_banner before do_build
