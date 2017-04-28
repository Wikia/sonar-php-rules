<?php
class Test {
    function bar() {
        wfMsg('foobar'); // NOK
        wfMsgExt('foobar', array('parsemag')); // NOK
        wfMsgHtml('foobar'); // NOK
        wfMsgForContent('foobar'); // NOK

        $this->msg( 'foobar' ); // OK
    }
}