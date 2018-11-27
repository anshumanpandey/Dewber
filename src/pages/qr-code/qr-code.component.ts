import { Component } from '@angular/core';
import { CommonService } from '../../providers/common';
import { BarcodeScanner } from '@ionic-native/barcode-scanner';

@Component({
    templateUrl: './qr-code.component.html'
})

export class QRCodeComponent {
    private header_data: string = null;
    private scannedData: string = null;
    constructor(
        // private qrScanner: QRScanner,
        private commonService: CommonService,
        private barcodeScanner: BarcodeScanner
    ) {
        this.header_data = "Scan QR Code";
    }

    scan() {
        this.commonService.toast('Start Scanning');
        this.barcodeScanner.scan().then((barcoadData: Object) => {
            this.scannedData = barcoadData["text"];
            this.commonService.toast(this.scannedData);
        }).catch(err => {
            this.commonService.toast(err);

        });

    }

}