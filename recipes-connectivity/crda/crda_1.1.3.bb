DESCRIPTION = "Central Regulatory Domain Agent"
SECTION = "base"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://LICENSE;md5=07c4f6dea3845b02a18dc00c8c87699c"


DEPENDS = "libnl"
RDEPENDS:${PN} = "\
	       wireless-regdb \
	       udev \
"

S = "${WORKDIR}/git"

SRC_URI = "git://github.com/mcgrof/crda.git;protocol=https;branch=master"
SRCREV = "47b1aaa36e770be587c33f0f5345fe8df550aabc"

CFLAGS:append =" -DCONFIG_LIBNL32 -I${STAGING_INCDIR}/libnl3"
LDFLAGS:append =" -lnl-3 -lnl-genl-3 -lm"

do_compile() {
        ${CC} ${CFLAGS} ${S}/reglib.c ${S}/crda.c -o crda ${LDFLAGS}
}

do_install() {
	install -m 0755 -d ${D}${bindir}
        install -m 0755 ${S}/crda ${D}${bindir}
	install -m 0755 -d ${D}${libdir}/udev/rules.d
	sed 's:$(SBINDIR):${bindir}/:' ${S}/udev/regulatory.rules > ${S}/udev/regulatory.rules.parsed
	install -m 0755 ${S}/udev/regulatory.rules.parsed ${D}${libdir}/udev/rules.d/85-regulatory.rules
}

FILES:${PN} += "${libdir}/udev/rules.d/85-regulatory.rules"
