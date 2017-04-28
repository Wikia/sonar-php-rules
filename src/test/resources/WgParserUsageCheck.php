<?php

class FooBar {
    public function test() {
        global $wgParser; // NOK
        $app = F::app();
        $wg = $app->wg;

        $app->wg->Parser->parse(); // NOK
        $wg->Parser->parse(); // NOK
    }
}