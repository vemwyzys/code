import sys

from modulegraph.modulegraph import MissingModule


def check(cmd, mf):
    m = mf.findNode("PyQt6")
    if m and not isinstance(m, MissingModule):
        try:
            # PyQt6 with sipconfig module, handled
            # by sip recipe
            import sipconfig  # noqa: F401

            return None

        except ImportError:
            pass

        # All imports are done from C code, hence not visible
        # for modulegraph
        # 1. Use of 'sip'
        # 2. Use of other modules, datafiles and C libraries
        #    in the PyQt5 package.
        try:
            mf.import_hook("sip", m)
        except ImportError:
            mf.import_hook("sip", m, level=1)

        return {"packages": ["PyQt6"]}

    return None
